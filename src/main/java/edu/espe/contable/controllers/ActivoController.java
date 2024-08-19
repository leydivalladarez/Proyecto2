package edu.espe.contable.controllers;

import edu.espe.contable.entities.Activo;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ActivoController {
    @Autowired
    private ActivoRepository activoRepository;

    @GetMapping("/activos")
    public List<Activo> activos(@RequestParam(required = false) String buscar) {
        if (buscar == null || buscar.isEmpty()) {
            return activoRepository.findAll();
        } else {
            return activoRepository.findByNombreContainingIgnoreCase(buscar);
        }
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<Activo> getActivo(@PathVariable Long id){
        Activo activo = activoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Activo no encontrado"));
        return ResponseEntity.ok(activo);
    }

    @PostMapping("/activos")
    public Activo addActivo(@RequestBody Activo activo){
        return activoRepository.save(activo);
    }

    @PutMapping("/activos/{id}")
    public ResponseEntity<Activo> updateActivo(@PathVariable Long id, @RequestBody Activo activoRequest){
        Activo activo = activoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Activo no encontrado"));

        if (activo != null){
            activo.setNombre(activoRequest.getNombre());
            activo.setPeriodosDepreciacionTotal(activoRequest.getPeriodosDepreciacionTotal());
            activo.setValorCompra(activoRequest.getValorCompra());

            activo.setTipoActivo(activoRequest.getTipoActivo());

            Activo activoUpdated = activoRepository.save(activo);
            return ResponseEntity.ok(activoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/activos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteActivo(@PathVariable Long id){
        Activo activo = activoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Activo no encontrado"));

        activoRepository.delete(activo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
