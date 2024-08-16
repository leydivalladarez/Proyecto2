package edu.espe.contable.controllers;

import edu.espe.contable.dtos.CiudadVentasDTO;
import edu.espe.contable.dtos.ReporteVentasDTO;
import edu.espe.contable.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
