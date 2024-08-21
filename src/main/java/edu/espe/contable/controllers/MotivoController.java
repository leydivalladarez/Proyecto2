package edu.espe.contable.controllers;

import edu.espe.contable.entities.Motivo;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.MotivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MotivoController {
    @Autowired
    private MotivoRepository motivoRepository;

    @GetMapping("/motivos")
    public List<Motivo> motivos(@RequestParam(required = false) String buscar){
        if(buscar == null || buscar.isEmpty()){
            return motivoRepository.findAll();
        }else{
            return motivoRepository.findByNombreContainingIgnoreCaseOrTipoContainingIgnoreCase(buscar, buscar);
        }
    }

    @GetMapping("/motivos/{codigo}")
    public ResponseEntity<Motivo> getmotivo(@PathVariable Long codigo){
        Motivo motivo = motivoRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("Motivo no encontrado"));
        return ResponseEntity.ok(motivo);
    }

    @PostMapping("/motivos")
    public Motivo addmotivo(@RequestBody Motivo motivo){
        return motivoRepository.save(motivo);
    }

    @PutMapping("/motivos/{codigo}")
    public ResponseEntity<Motivo> updatemotivo(@PathVariable Long codigo, @RequestBody Motivo motivoRequest){
        Motivo motivo = motivoRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Motivo no encontrado"));

        if (motivo != null){
            motivo.setNombre(motivoRequest.getNombre());
            motivo.setTipo(motivoRequest.getTipo());

            Motivo motivoUpdated = motivoRepository.save(motivo);
            return ResponseEntity.ok(motivoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/motivos/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deletemotivo(@PathVariable Long codigo){
        Motivo motivo = motivoRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Motivo no encontrado"));

        motivoRepository.delete(motivo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
