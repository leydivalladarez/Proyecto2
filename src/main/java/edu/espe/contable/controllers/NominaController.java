package edu.espe.contable.controllers;

import edu.espe.contable.entities.*;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class NominaController {
    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private NominaDetalleRepository nominaDetalleRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private MotivoRepository motivoRepository;

    @GetMapping("/nominas")
    public List<Nomina> nominas(@RequestParam(required = false) String numero,
                                @RequestParam(required = false) String nombre) {
        Long numeroLong = null;
        if (numero != null) {
            try {
                numeroLong = Long.parseLong(numero);
            } catch (NumberFormatException ignored) {}
        }
        if (numeroLong == null && nombre == null) {
            return nominaRepository.findAll();
        }else{
            return nominaRepository.findByNumeroOrEmpleado_NombreContainsIgnoreCase(numeroLong, nombre);
        }
    }

    @GetMapping("/nominas/{numero}")
    public ResponseEntity<Nomina> getNomina(@PathVariable Long numero) {
        Nomina nomina = nominaRepository.findById(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Nomina no encontrada"));
        return ResponseEntity.ok(nomina);
    }

    @Transactional
    @PostMapping("/nominas")
    public Nomina addNomina(@RequestBody Nomina nomina) {
        Long empleadoId = nomina.getEmpleado().getId();
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrada"));
        nomina.setEmpleado(empleado);
        
        // Guardar la nomina principal
        Nomina savedNomina = nominaRepository.save(nomina);

        // Guardar los detalles de la nomina
        saveDetalle(nomina, savedNomina);

        return savedNomina;
    }

    @Transactional
    @PutMapping("/nominas/{numero}")
    public ResponseEntity<Nomina> updateNomina(@PathVariable Long numero, @RequestBody Nomina nominaRequest) {
        Nomina nomina = nominaRepository.findById(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Nomina no encontrada"));

        // Actualizar los campos de la nomina principal
        nomina.setFecha(nominaRequest.getFecha());
        nomina.setEmpleado(nominaRequest.getEmpleado());

        // Actualizar los detalles de la nomina
        nominaDetalleRepository.deleteByNomina(nomina); // Borra los detalles existentes
        saveDetalle(nominaRequest, nomina);

        // Guardar la nomina actualizada
        Nomina nominaUpdated = nominaRepository.save(nomina);

        return ResponseEntity.ok(nominaUpdated);
    }

    private void saveDetalle(@RequestBody Nomina nominaRequest, Nomina nomina) {
        for (NominaDetalle detalle : nominaRequest.getNominaDetalles()) {
            Long motivoCodigo = detalle.getMotivo().getCodigo();
            Motivo motivo = motivoRepository.findById(motivoCodigo)
                    .orElseThrow(() -> new ResourceNotFoundException("Motivo no encontrado"));
            detalle.setMotivo(motivo);
            detalle.setNomina(nomina); // Asocia el detalle con la nomina
            nominaDetalleRepository.save(detalle);
        }
    }


    @DeleteMapping("/nominas/{numero}")
    public ResponseEntity<Map<String, Boolean>> deleteNomina(@PathVariable Long numero) {
        Nomina nomina = nominaRepository.findById(numero).orElseThrow(() -> new ResourceNotFoundException("Nomina no encontrada"));

        nominaRepository.delete(nomina);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
