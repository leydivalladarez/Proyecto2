package edu.espe.contable.controllers;

import edu.espe.contable.dtos.CiudadVentasDTO;
import edu.espe.contable.entities.Ciudad;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class CiudadController {
    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudades")
    public List<Ciudad> ciudades(){
        return ciudadRepository.findAll();
    }

    @GetMapping("/ciudades/{codigo}")
    public ResponseEntity<Ciudad> getCiudad(@PathVariable Long codigo){
        Ciudad ciudad = ciudadRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada"));
        return ResponseEntity.ok(ciudad);
    }

    @PostMapping("/ciudades")
    public Ciudad addCiudad(@RequestBody Ciudad ciudad){
        return ciudadRepository.save(ciudad);
    }

    @PutMapping("/ciudades/{codigo}")
    public ResponseEntity<Ciudad> updateCiudad(@PathVariable Long codigo, @RequestBody Ciudad ciudadRequest){
        Ciudad ciudad = ciudadRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada"));

        if (ciudad != null){
            ciudad.setNombre(ciudadRequest.getNombre());

            Ciudad ciudadUpdated = ciudadRepository.save(ciudad);
            return ResponseEntity.ok(ciudadUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/ciudades/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deleteCiudad(@PathVariable Long codigo){
        Ciudad ciudad = ciudadRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada"));

        ciudadRepository.delete(ciudad);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
