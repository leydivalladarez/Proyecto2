package edu.espe.contable.services;

import edu.espe.contable.dtos.CiudadVentasDTO;
import edu.espe.contable.dtos.ReporteMotivosDTO;
import edu.espe.contable.dtos.ReporteVentasDTO;
import edu.espe.contable.dtos.ValorAPagarDTO;
import edu.espe.contable.entities.Articulo;
import edu.espe.contable.entities.Empleado;
import edu.espe.contable.entities.FacturaDetalle;
import edu.espe.contable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<ReporteVentasDTO> generarReporteVentas() {
        List<Articulo> articulos = articuloRepository.findAll();

        return articulos.stream().map(articulo -> {
            Map<Long, BigDecimal> ventasPorCliente = articulo.getFacturaDetalles().stream()
                    .collect(Collectors.groupingBy(
                            detalle -> detalle.getFactura().getCliente().getId(),
                            Collectors.reducing(BigDecimal.ZERO,
                                    detalle -> detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())),
                                    BigDecimal::add)
                    ));
            return new ReporteVentasDTO(articulo.getCodigo(), articulo.getNombre(), ventasPorCliente);
        }).collect(Collectors.toList());
    }

    public List<ReporteVentasDTO> generarReporteVentasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
        List<Articulo> articulos = articuloRepository.findAll();

        return articulos.stream().map(articulo -> {
            // Filtra los detalles de factura por el rango de fechas
            Map<Long, BigDecimal> ventasPorCliente = articulo.getFacturaDetalles().stream()
                    .filter(detalle ->
                            detalle.getFactura().getFecha().isAfter(fechaInicio.minusDays(1)) &&
                                    detalle.getFactura().getFecha().isBefore(fechaFinal.plusDays(1))
                    )
                    .collect(Collectors.groupingBy(
                            detalle -> detalle.getFactura().getCliente().getId(),
                            Collectors.reducing(BigDecimal.ZERO,
                                    detalle -> detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())),
                                    BigDecimal::add)
                    ));
            return new ReporteVentasDTO(articulo.getCodigo(), articulo.getNombre(), ventasPorCliente);
        }).collect(Collectors.toList());
    }


    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<ValorAPagarDTO> obtenerValoresAPagar() {
        List<ValorAPagarProjection> proyecciones = nominaRepository.findValoresAPagar();
        return proyecciones.stream()
                .map(p -> new ValorAPagarDTO(p.getNombreEmpleado(), p.getValorTotal()))
                .collect(Collectors.toList());
    }

    public List<ValorAPagarDTO> obtenerValoresAPagarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
        List<ValorAPagarProjection> proyecciones = nominaRepository.findValoresAPagarPorRangoFechas(fechaInicio, fechaFinal);
        return proyecciones.stream()
                .map(p -> new ValorAPagarDTO(p.getNombreEmpleado(), p.getValorTotal()))
                .collect(Collectors.toList());
    }

    public List<ReporteMotivosDTO> generarReporteMotivos() {
        List<Empleado> empleados = empleadoRepository.findAll();

        return empleados.stream().map(empleado -> {
            Map<Long, BigDecimal> motivosPorEmpleado = empleado.getNominas().stream()
                    .flatMap(nomina -> nomina.getNominaDetalles().stream())
                    .collect(Collectors.groupingBy(
                            detalle -> detalle.getMotivo().getCodigo(),
                            Collectors.reducing(BigDecimal.ZERO,
                                    detalle -> detalle.getMotivo().getTipo().equalsIgnoreCase("ingreso")
                                            ? detalle.getValor()
                                            : detalle.getValor().negate(),
                                    BigDecimal::add)
                    ));
            return new ReporteMotivosDTO(empleado.getId(), empleado.getNombre(), motivosPorEmpleado);
        }).collect(Collectors.toList());
    }

    public List<ReporteMotivosDTO> generarReporteMotivosRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Empleado> empleados = empleadoRepository.findAll();

        return empleados.stream().map(empleado -> {
            Map<Long, BigDecimal> motivosPorEmpleado = empleado.getNominas().stream()
                    .filter(nomina -> !nomina.getFecha().isBefore(fechaInicio) && !nomina.getFecha().isAfter(fechaFin))
                    .flatMap(nomina -> nomina.getNominaDetalles().stream())
                    .collect(Collectors.groupingBy(
                            detalle -> detalle.getMotivo().getCodigo(),
                            Collectors.reducing(BigDecimal.ZERO,
                                    detalle -> detalle.getMotivo().getTipo().equalsIgnoreCase("ingreso")
                                            ? detalle.getValor()
                                            : detalle.getValor().negate(),
                                    BigDecimal::add)
                    ));
            return new ReporteMotivosDTO(empleado.getId(), empleado.getNombre(), motivosPorEmpleado);
        }).collect(Collectors.toList());
    }

}
