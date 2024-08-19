package edu.espe.contable.controllers;

import edu.espe.contable.dtos.*;
import edu.espe.contable.entities.Activo;
import edu.espe.contable.entities.DepreciacionDetalle;
import edu.espe.contable.repository.DepreciacionDetalleRepository;
import edu.espe.contable.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("ventas-totales-ciudades")
    public ResponseEntity<List<CiudadVentasDTO>> obtenerTodasLasCiudadesConVentasTotales() {
        List<CiudadVentasDTO> ciudadesVentas = reporteService.obtenerTodasLasCiudadesConVentasTotales();
        return ResponseEntity.ok(ciudadesVentas);
    }

    @GetMapping("/ventas-cruzadas")
    public ResponseEntity<List<ReporteVentasDTO>> obtenerReporteVentasCruzadas() {
        List<ReporteVentasDTO> reporte = reporteService.generarReporteVentas();
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/valores-a-pagar")
    public ResponseEntity<List<ValorAPagarDTO>> obtenerValoresAPagar() {
        List<ValorAPagarDTO> valoresAPagar = reporteService.obtenerValoresAPagar();
        return ResponseEntity.ok(valoresAPagar);
    }

    @GetMapping("/reporte-motivos")
    public ResponseEntity<List<ReporteMotivosDTO>> obtenerReporteMotivos() {
        List<ReporteMotivosDTO> reporte = reporteService.generarReporteMotivos();
        return ResponseEntity.ok(reporte);
    }

    @Autowired
    DepreciacionDetalleRepository depreciacionDetalleRepository;

    @GetMapping("/depreciaciones")
    public ResponseEntity<List<DepreciacionReporteDTO>> reporteDepreciacion(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<DepreciacionDetalle> detalles = depreciacionDetalleRepository.findByDepreciacion_FechaBetween(fechaInicio, fechaFin);

        Map<Activo, Double> reporte = new HashMap<>();
        for (DepreciacionDetalle detalle : detalles) {
            reporte.merge(detalle.getActivo(), detalle.getValorDepreciacion().doubleValue(), Double::sum);
        }

        List<DepreciacionReporteDTO> reporteDTOs = reporte.entrySet().stream()
                .map(entry -> new DepreciacionReporteDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reporteDTOs);
    }

}
