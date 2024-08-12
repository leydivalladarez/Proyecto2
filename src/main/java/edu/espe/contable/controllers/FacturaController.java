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
@RequestMapping("/api/v1")
public class FacturaController {
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping("/facturas")
    public List<Factura> facturas() {
        return facturaRepository.findAll();
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrado"));
        return ResponseEntity.ok(factura);
    }

    @Transactional
    @PostMapping("/facturas")
    public Factura addFactura(@RequestBody Factura factura) {
        Long clienteId = factura.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        factura.setCliente(cliente);

        Long ciudadCodigo = factura.getCiudad().getCodigo();
        Ciudad ciudad = ciudadRepository.findById(ciudadCodigo)
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));
        factura.setCiudad(ciudad);
        // Guardar la factura principal
        Factura savedFactura = facturaRepository.save(factura);

        // Guardar los detalles de la factura
        saveDetalle(factura, savedFactura);

        return savedFactura;
    }

    @Transactional
    @PutMapping("/facturas/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura facturaRequest) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));

        // Actualizar los campos de la factura principal
        factura.setFecha(facturaRequest.getFecha());
        factura.setCliente(facturaRequest.getCliente());
        factura.setCiudad(facturaRequest.getCiudad());

        // Actualizar los detalles de la factura
        facturaDetalleRepository.deleteByFactura(factura); // Borra los detalles existentes
        saveDetalle(facturaRequest, factura);

        // Guardar la factura actualizada
        Factura facturaUpdated = facturaRepository.save(factura);

        return ResponseEntity.ok(facturaUpdated);
    }

    private void saveDetalle(@RequestBody Factura facturaRequest, Factura factura) {
        for (FacturaDetalle detalle : facturaRequest.getFacturaDetalles()) {
            Long articuloCodigo = detalle.getArticulo().getCodigo();
            Articulo articulo = articuloRepository.findById(articuloCodigo)
                    .orElseThrow(() -> new ResourceNotFoundException("Artículo no encontrado"));
            detalle.setArticulo(articulo);
            detalle.setFactura(factura); // Asocia el detalle con la factura
            facturaDetalleRepository.save(detalle);
        }
    }


    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFactura(@PathVariable Long id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Factura no encontrado"));

        facturaRepository.delete(factura);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
