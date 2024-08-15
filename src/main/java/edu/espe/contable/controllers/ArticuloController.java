package edu.espe.contable.controllers;

import edu.espe.contable.entities.Articulo;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class ArticuloController {
    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping("/articulos")
    public List<Articulo> articulos(){
        return articuloRepository.findAll();
    }

    @GetMapping("/articulos/{codigo}")
    public ResponseEntity<Articulo> getArticulo(@PathVariable Long codigo){
        Articulo articulo = articuloRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("Articulo no encontrado"));
        return ResponseEntity.ok(articulo);
    }

    @PostMapping("/articulos")
    public Articulo addArticulo(@RequestBody Articulo articulo){
        return articuloRepository.save(articulo);
    }

    @PutMapping("/articulos/{codigo}")
    public ResponseEntity<Articulo> updateArticulo(@PathVariable Long codigo, @RequestBody Articulo articuloRequest){
        Articulo articulo = articuloRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Articulo no encontrado"));

        if (articulo != null){
            articulo.setNombre(articuloRequest.getNombre());
            articulo.setPrecio(articuloRequest.getPrecio());

            Articulo articuloUpdated = articuloRepository.save(articulo);
            return ResponseEntity.ok(articuloUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/articulos/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deleteArticulo(@PathVariable Long codigo){
        Articulo articulo = articuloRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Articulo no encontrado"));

        articuloRepository.delete(articulo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
