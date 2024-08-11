package edu.espe.contable.controllers;

import edu.espe.contable.entities.Factura;
import edu.espe.contable.entities.FacturaDetalle;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.FacturaDetalleRepository;
import edu.espe.contable.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FacturaController {
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @GetMapping("/facturas")
    public List<Factura> facturas(){
        return facturaRepository.findAll();
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable Long id){
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Factura no encontrado"));
        return ResponseEntity.ok(factura);
    }

    @Transactional
    @PostMapping("/facturas")
    public Factura addFactura(@RequestBody Factura factura){
        // Guardar la factura principal
        Factura savedFactura = facturaRepository.save(factura);

        // Guardar los detalles de la factura
        for (FacturaDetalle detalle : factura.getFacturaDetalles()) {
            detalle.setFactura(savedFactura); // Asocia el detalle con la factura guardada
            facturaDetalleRepository.save(detalle);
        }

        return savedFactura;
    }

    @Transactional
    @PutMapping("/facturas/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura facturaRequest) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));

        // Actualizar los campos de la factura principal
        factura.setFecha(facturaRequest.getFecha());
        factura.setClienteId(facturaRequest.getClienteId());
        factura.setCiudadCodigo(facturaRequest.getCiudadCodigo());

        // Actualizar los detalles de la factura
        facturaDetalleRepository.deleteByFactura(factura); // Borra los detalles existentes
        for (FacturaDetalle detalle : facturaRequest.getFacturaDetalles()) {
            detalle.setFactura(factura); // Asocia el detalle con la factura
            facturaDetalleRepository.save(detalle);
        }

        // Guardar la factura actualizada
        Factura facturaUpdated = facturaRepository.save(factura);

        return ResponseEntity.ok(facturaUpdated);
    }


    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFactura(@PathVariable Long id){
        Factura factura = facturaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Factura no encontrado"));

        facturaRepository.delete(factura);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
