package edu.espe.contable.controllers;

import edu.espe.contable.dtos.*;
import edu.espe.contable.entities.Activo;
import edu.espe.contable.entities.DepreciacionDetalle;
import edu.espe.contable.repository.CiudadRepository;
import edu.espe.contable.repository.DepreciacionDetalleRepository;
import edu.espe.contable.repository.NominaRepository;
import edu.espe.contable.services.BalanceService;
import edu.espe.contable.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @Autowired
    private CiudadRepository ciudadRepository;

    //Reportes de Facturación
    @GetMapping("ventas-totales-ciudades")
    public ResponseEntity<List<CiudadVentasDTO>> obtenerTodasLasCiudadesConVentasTotales(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal
    ) {
        List<CiudadVentasDTO> ciudadesVentas;
        if(fechaInicio == null || fechaFinal == null) {
            ciudadesVentas = ciudadRepository.ventasPorCiudades();
        }else{
            ciudadesVentas = ciudadRepository.ventasPorCiudadesEnRangoFechas(fechaInicio, fechaFinal);
        }
        return ResponseEntity.ok(ciudadesVentas);
    }

    @GetMapping("/ventas-cruzadas")
    public ResponseEntity<List<ReporteVentasDTO>> obtenerReporteVentasCruzadas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal
    ) {
        List<ReporteVentasDTO> reporte;
        if(fechaInicio == null || fechaFinal == null) {
            reporte = reporteService.generarReporteVentas();
        }else{
            reporte = reporteService.generarReporteVentasPorRangoFechas(fechaInicio, fechaFinal);
        }
        return ResponseEntity.ok(reporte);
    }

    //Reportes de Nómina
    @Autowired
    NominaRepository nominaRepository;

    @GetMapping("/valores-a-pagar")
    public ResponseEntity<List<ValorAPagarDTO>> obtenerValoresAPagar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal
    ) {
        List<ValorAPagarDTO> valoresAPagar;
        if(fechaInicio == null || fechaFinal == null) {
            valoresAPagar = reporteService.obtenerValoresAPagar();
        }else{
            valoresAPagar = reporteService.obtenerValoresAPagarPorRangoFechas(fechaInicio, fechaFinal);
        }
        return ResponseEntity.ok(valoresAPagar);
    }

    @GetMapping("/reporte-motivos")
    public ResponseEntity<List<ReporteMotivosDTO>> obtenerReporteMotivos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal
    ) {
        List<ReporteMotivosDTO> reporte;
        if(fechaInicio == null || fechaFinal == null) {
            reporte = reporteService.generarReporteMotivos();
        }else{
            reporte = reporteService.generarReporteMotivosRangoFechas(fechaInicio, fechaFinal);
        }
        return ResponseEntity.ok(reporte);
    }

    //Reportes de Activos
    @Autowired
    DepreciacionDetalleRepository depreciacionDetalleRepository;

    @GetMapping("/depreciaciones")
    public ResponseEntity<List<DepreciacionReporteDTO>> reporteDepreciacion(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<DepreciacionDetalle> detalles;
        if(fechaInicio == null || fechaFin == null) {
            detalles = depreciacionDetalleRepository.findAll();
        }else{
            detalles = depreciacionDetalleRepository.findByDepreciacion_FechaBetween(fechaInicio, fechaFin);
        }

        Map<Activo, Double> reporte = new HashMap<>();
        for (DepreciacionDetalle detalle : detalles) {
            reporte.merge(detalle.getActivo(), detalle.getValorDepreciacion().doubleValue(), Double::sum);
        }

        List<DepreciacionReporteDTO> reporteDTOs = reporte.entrySet().stream()
                .map(entry -> new DepreciacionReporteDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reporteDTOs);
    }

    //Reportes de Contabilidad
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/balance-general")
    public ResponseEntity<List<CuentaBalanceDTO>> getBalanceGeneral(
            @RequestParam Optional<LocalDate> fechaInicio,
            @RequestParam Optional<LocalDate> fechaFin) {
        List<CuentaBalanceDTO> balance = balanceService.getBalanceGeneral(fechaInicio, fechaFin);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/estado-resultados")
    public ResponseEntity<List<CuentaResultadoDTO>> getEstadoResultados(
            @RequestParam Optional<LocalDate> fechaInicio,
            @RequestParam Optional<LocalDate> fechaFin) {
        List<CuentaResultadoDTO> balance = balanceService.getEstadoResultados(fechaInicio, fechaFin);
        return ResponseEntity.ok(balance);
    }
}
