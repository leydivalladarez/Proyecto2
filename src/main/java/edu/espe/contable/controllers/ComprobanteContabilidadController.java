package edu.espe.contable.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.espe.contable.Views;
import edu.espe.contable.entities.*;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ComprobanteContabilidadController {
    @Autowired
    private ComprobanteContabilidadRepository comprobanteContabilidadRepository;

    @Autowired
    private ComprobanteContabilidadDetalleRepository comprobanteContabilidadDetalleRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/comprobantesContabilidad")
    @JsonView(Views.Simple.class)
    public List<ComprobanteContabilidad> comprobantesContabilidad(
            @RequestParam(required = false) String numero,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        Long numeroLong = null;
        if (numero != null) {
            try {
                numeroLong = Long.parseLong(numero);
            } catch (NumberFormatException ignored) {}
        }

        if (numeroLong == null && fecha == null) {
            return comprobanteContabilidadRepository.findAll();
        }else{
            return comprobanteContabilidadRepository.findByNumeroOrFecha(numeroLong,fecha);
        }
    }

    @GetMapping("/comprobantesContabilidad/{numero}")
    @JsonView(Views.Simple.class)
    public ResponseEntity<ComprobanteContabilidad> getComprobanteContabilidad(@PathVariable Long numero) {
        ComprobanteContabilidad comprobanteContabilidad = comprobanteContabilidadRepository.findById(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Comprobante Contabilidad no encontrado"));
        return ResponseEntity.ok(comprobanteContabilidad);
    }

    @Transactional
    @PostMapping("/comprobantesContabilidad")
    @JsonView(Views.Simple.class)
    public ComprobanteContabilidad addComprobanteContabilidad(@RequestBody ComprobanteContabilidad comprobanteContabilidad) {

        // Guardar la comprobanteContabilidad principal
        ComprobanteContabilidad savedComprobanteContabilidad = comprobanteContabilidadRepository.save(comprobanteContabilidad);

        // Guardar los detalles de la comprobanteContabilidad
        saveDetalle(comprobanteContabilidad, savedComprobanteContabilidad);

        return savedComprobanteContabilidad;
    }

    @Transactional
    @PutMapping("/comprobantesContabilidad/{numero}")
    @JsonView(Views.Simple.class)
    public ResponseEntity<ComprobanteContabilidad> updateComprobanteContabilidad(@PathVariable Long numero, @RequestBody ComprobanteContabilidad comprobanteContabilidadRequest) {
        ComprobanteContabilidad comprobanteContabilidad = comprobanteContabilidadRepository.findById(numero)
                .orElseThrow(() -> new ResourceNotFoundException("ComprobanteContabilidad no encontrada"));

        // Actualizar los campos de la comprobanteContabilidad principal
        comprobanteContabilidad.setNumero(comprobanteContabilidadRequest.getNumero());
        comprobanteContabilidad.setFecha(comprobanteContabilidadRequest.getFecha());
        comprobanteContabilidad.setObservaciones(comprobanteContabilidadRequest.getObservaciones());

        // Actualizar los detalles de la comprobanteContabilidad
        comprobanteContabilidadDetalleRepository.deleteByComprobanteContabilidad(comprobanteContabilidad); // Borra los detalles existentes
        saveDetalle(comprobanteContabilidadRequest, comprobanteContabilidad);

        // Guardar la comprobanteContabilidad actualizada
        ComprobanteContabilidad comprobanteContabilidadUpdated = comprobanteContabilidadRepository.save(comprobanteContabilidad);

        return ResponseEntity.ok(comprobanteContabilidadUpdated);
    }

    private void saveDetalle(@RequestBody ComprobanteContabilidad comprobanteContabilidadRequest, ComprobanteContabilidad comprobanteContabilidad) {
        // Inicialización de las sumas de debe y haber
        BigDecimal debe = BigDecimal.ZERO;
        BigDecimal haber = BigDecimal.ZERO;

        // Suma de los valores de debe y haber
        for (ComprobanteContabilidadDetalle detalle : comprobanteContabilidadRequest.getComprobanteContabilidadDetalles()) {
            if (detalle.getDebe() != null) {
                debe = debe.add(detalle.getDebe());
            }
            if (detalle.getHaber() != null) {
                haber = haber.add(detalle.getHaber());
            }
        }

        // Validación de encuadre de asientos
        if (debe.compareTo(haber) != 0) {
            throw new IllegalArgumentException("La suma del (Debe "+debe+") debe ser igual a la suma del (Haber "+haber+").");
        }

        for (ComprobanteContabilidadDetalle detalle : comprobanteContabilidadRequest.getComprobanteContabilidadDetalles()) {
            Long cuentaCodigo = detalle.getCuenta().getCodigo();
            Cuenta cuenta = cuentaRepository.findById(cuentaCodigo)
                    .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
            detalle.setCuenta(cuenta);
            detalle.setComprobanteContabilidad(comprobanteContabilidad); // Asocia el detalle con la comprobanteContabilidad
            comprobanteContabilidadDetalleRepository.save(detalle);
        }
    }

    @DeleteMapping("/comprobantesContabilidad/{numero}")
    public ResponseEntity<Map<String, Boolean>> deleteComprobanteContabilidad(@PathVariable Long numero) {
        ComprobanteContabilidad comprobanteContabilidad = comprobanteContabilidadRepository.findById(numero).orElseThrow(() -> new ResourceNotFoundException("Comprobante Contabilidad no encontrada"));

        comprobanteContabilidadRepository.delete(comprobanteContabilidad);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
