package edu.espe.contable.services;

import edu.espe.contable.dtos.CuentaBalanceDTO;
import edu.espe.contable.dtos.CuentaResultadoDTO;
import edu.espe.contable.entities.ComprobanteContabilidadDetalle;
import edu.espe.contable.entities.Cuenta;
import edu.espe.contable.entities.TipoCuenta;
import edu.espe.contable.repository.ComprobanteContabilidadDetalleRepository;
import edu.espe.contable.repository.CuentaRepository;
import edu.espe.contable.repository.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BalanceService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ComprobanteContabilidadDetalleRepository detalleRepository;

    public List<CuentaBalanceDTO> getBalanceGeneral(Optional<LocalDate> fechaInicio, Optional<LocalDate> fechaFin) {
        // Obtener los detalles de los comprobantes dentro del rango de fechas (si es que se especifican)
        List<ComprobanteContabilidadDetalle> detalles;
        if (fechaInicio.isPresent() && fechaFin.isPresent()) {
            detalles = detalleRepository.findByComprobanteFechaBetween(fechaInicio.get(), fechaFin.get());
        } else {
            detalles = detalleRepository.findAll();
        }

        // Agrupar los detalles por cuenta y calcular los saldos
        Map<Cuenta, BigDecimal[]> saldos = new HashMap<>();
        for (ComprobanteContabilidadDetalle detalle : detalles) {
            saldos.computeIfAbsent(detalle.getCuenta(), k -> new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO});
            saldos.get(detalle.getCuenta())[0] = saldos.get(detalle.getCuenta())[0].add(detalle.getDebe() != null ? detalle.getDebe() : BigDecimal.ZERO);
            saldos.get(detalle.getCuenta())[1] = saldos.get(detalle.getCuenta())[1].add(detalle.getHaber() != null ? detalle.getHaber() : BigDecimal.ZERO);
        }

        // Construir el balance general a partir de los saldos
        return saldos.entrySet().stream()
                .map(entry -> new CuentaBalanceDTO(
                        entry.getKey().getCodigo(),
                        entry.getKey().getNombre(),
                        entry.getKey().getTipoCuenta(),
                        entry.getValue()[0],
                        entry.getValue()[1],
                        entry.getKey().getCuentas()))
                .collect(Collectors.toList());
    }

    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;

    public List<CuentaResultadoDTO> getEstadoResultados(Optional<LocalDate> fechaInicio, Optional<LocalDate> fechaFin) {
        // Busca las instancias de TipoCuenta en la base de datos
        TipoCuenta tipoCuentaIngreso = tipoCuentaRepository.findByNombre("Ingreso");
        TipoCuenta tipoCuentaEgreso = tipoCuentaRepository.findByNombre("Egreso");

        // 1. Obtener todas las cuentas principales relevantes (por ejemplo, de tipo Ingreso y Egreso)
        List<Cuenta> cuentasPrincipales = cuentaRepository.findByTipoCuentaIn(Arrays.asList(tipoCuentaIngreso, tipoCuentaEgreso));

        // 2. Obtener los detalles de los comprobantes dentro del rango de fechas especificado
        List<ComprobanteContabilidadDetalle> detalles;
        if (fechaInicio.isPresent() && fechaFin.isPresent()) {
            detalles = detalleRepository.findByComprobanteFechaBetween(fechaInicio.get(), fechaFin.get());
        } else {
            detalles = detalleRepository.findAll();
        }

        // 3. Mapear los detalles a las cuentas correspondientes
        Map<Long, BigDecimal[]> saldoPorCuentaId = new HashMap<>();
        for (ComprobanteContabilidadDetalle detalle : detalles) {
            Long cuentaCodigo = detalle.getCuenta().getCodigo();
            saldoPorCuentaId.putIfAbsent(cuentaCodigo, new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO});
            BigDecimal debe = detalle.getDebe() != null ? detalle.getDebe() : BigDecimal.ZERO;
            BigDecimal haber = detalle.getHaber() != null ? detalle.getHaber() : BigDecimal.ZERO;
            saldoPorCuentaId.get(cuentaCodigo)[0] = saldoPorCuentaId.get(cuentaCodigo)[0].add(debe);
            saldoPorCuentaId.get(cuentaCodigo)[1] = saldoPorCuentaId.get(cuentaCodigo)[1].add(haber);
        }

        // 4. Construir la estructura jerárquica de cuentas con saldos
        List<CuentaResultadoDTO> resultado = cuentasPrincipales.stream()
                .filter(cuenta -> cuenta.getCuentaPadre() == null)
                .map(cuenta -> construirCuentaBalanceDTO(cuenta, saldoPorCuentaId))
                .collect(Collectors.toList());


        return resultado;
    }

    private CuentaResultadoDTO construirCuentaBalanceDTO(Cuenta cuenta, Map<Long, BigDecimal[]> saldoPorCuentaId) {
        // Obtener saldo de la cuenta actual
        BigDecimal debe = saldoPorCuentaId.getOrDefault(cuenta.getCodigo(), new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO})[0];
        BigDecimal haber = saldoPorCuentaId.getOrDefault(cuenta.getCodigo(), new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO})[1];

        // Construir lista de subcuentas recursivamente sin incluir la referencia al padre
        List<CuentaResultadoDTO> subCuentas = cuenta.getCuentas().stream()
                .map(subCuenta -> construirCuentaBalanceDTO(subCuenta, saldoPorCuentaId))
                .collect(Collectors.toList());

        // Sumar los saldos de las subcuentas a la cuenta padre
        for (CuentaResultadoDTO subCuentaDTO : subCuentas) {
            debe = debe.add(subCuentaDTO.getSaldoDebe());
            haber = haber.add(subCuentaDTO.getSaldoHaber());
        }

        // Crear y retornar el DTO sin la referencia al padre
        return new CuentaResultadoDTO(
                cuenta.getCodigo(),
                cuenta.getNombre(),
                cuenta.getTipoCuenta(),
                debe,
                haber,
                subCuentas
        );
    }


}