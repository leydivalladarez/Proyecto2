package edu.espe.contable.controllers;

import edu.espe.contable.entities.*;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class DepreciacionController {
    @Autowired
    private DepreciacionRepository depreciacionRepository;

    @Autowired
    private DepreciacionDetalleRepository depreciacionDetalleRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private TipoActivoRepository tipoActivoRepository;

    @GetMapping("/depreciaciones")
    public List<Depreciacion> depreciaciones(@RequestParam(required = false) String numero,
                                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
                                             @RequestParam(required = false) String responsable) {
        Long numeroL = null;
        if(numero != null){
            try {
                numeroL = Long.parseLong(numero);
            } catch (NumberFormatException ignored) {}
        }
        if (numeroL == null && fecha == null && responsable == null) {
            return depreciacionRepository.findAll();
        } else {
            return depreciacionRepository.findByNumeroOrFechaOrResponsableContainingIgnoreCase(numeroL, fecha, responsable);
        }
    }

    @GetMapping("/depreciaciones/{id}")
    public ResponseEntity<Depreciacion> getDepreciacion(@PathVariable Long id) {
        Depreciacion depreciacion = depreciacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciacion no encontrado"));
        return ResponseEntity.ok(depreciacion);
    }

    @Transactional
    @PostMapping("/depreciaciones")
    public Depreciacion addDepreciacion(@RequestBody Depreciacion depreciacion) {
        // Guardar la depreciacion principal
        Depreciacion savedDepreciacion = depreciacionRepository.save(depreciacion);

        // Guardar los detalles de la depreciacion
        saveDetalle(depreciacion, savedDepreciacion);

        return savedDepreciacion;
    }

    @Transactional
    @PutMapping("/depreciaciones/{id}")
    public ResponseEntity<Depreciacion> updateDepreciacion(@PathVariable Long id, @RequestBody Depreciacion depreciacionRequest) {
        Depreciacion depreciacion = depreciacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciacion no encontrada"));

        // Actualizar los campos de la depreciacion principal
        depreciacion.setFecha(depreciacionRequest.getFecha());
        depreciacion.setObservaciones(depreciacionRequest.getObservaciones());
        depreciacion.setResponsable(depreciacionRequest.getResponsable());

        // Actualizar los detalles de la depreciacion
        depreciacionDetalleRepository.deleteByDepreciacion(depreciacion); // Borra los detalles existentes
        saveDetalle(depreciacionRequest, depreciacion);

        // Guardar la depreciacion actualizada
        Depreciacion depreciacionUpdated = depreciacionRepository.save(depreciacion);

        return ResponseEntity.ok(depreciacionUpdated);
    }

    private void saveDetalle(Depreciacion depreciacionRequest, Depreciacion depreciacion) {
        depreciacionRequest.getDepreciacionDetalles().forEach(detalle -> {
            Activo activo = activoRepository.findById(detalle.getActivo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Activo no encontrado"));
            activo.setPeriodosDepreciacionTotal(detalle.getPeriodoDepreciacion());

            detalle.setDepreciacion(depreciacion);
            detalle.setActivo(activo);
             // Asocia el detalle con la depreciacion
            depreciacionDetalleRepository.save(detalle);
            activoRepository.save(activo);
        });
    }

    @DeleteMapping("/depreciaciones/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDepreciacion(@PathVariable Long id) {
        Depreciacion depreciacion = depreciacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Depreciacion no encontrado"));

        depreciacionRepository.delete(depreciacion);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
