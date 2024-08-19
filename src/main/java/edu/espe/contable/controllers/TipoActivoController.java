package edu.espe.contable.controllers;

import edu.espe.contable.entities.TipoActivo;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.TipoActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class TipoActivoController {
    @Autowired
    private TipoActivoRepository tipoActivoRepository;

    @GetMapping("/tipoActivos")
    public List<TipoActivo> tipoActivos(@RequestParam(required = false) String buscar){
        if(buscar == null || buscar.isEmpty()){
            return tipoActivoRepository.findAll();
        }else {
            return tipoActivoRepository.findByNombreContainingIgnoreCase(buscar);
        }
    }

    @GetMapping("/tipoActivos/{codigo}")
    public ResponseEntity<TipoActivo> getTipoActivo(@PathVariable Long codigo){
        TipoActivo tipoActivo = tipoActivoRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("TipoActivo no encontrado"));
        return ResponseEntity.ok(tipoActivo);
    }

    @PostMapping("/tipoActivos")
    public TipoActivo addTipoActivo(@RequestBody TipoActivo tipoActivo){
        return tipoActivoRepository.save(tipoActivo);
    }

    @PutMapping("/tipoActivos/{codigo}")
    public ResponseEntity<TipoActivo> updateTipoActivo(@PathVariable Long codigo, @RequestBody TipoActivo tipoActivoRequest){
        TipoActivo tipoActivo = tipoActivoRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("TipoActivo no encontrado"));

        if (tipoActivo != null){
            tipoActivo.setNombre(tipoActivoRequest.getNombre());

            TipoActivo tipoActivoUpdated = tipoActivoRepository.save(tipoActivo);
            return ResponseEntity.ok(tipoActivoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tipoActivos/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deleteTipoActivo(@PathVariable Long codigo){
        TipoActivo tipoActivo = tipoActivoRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("TipoActivo no encontrado"));

        tipoActivoRepository.delete(tipoActivo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
