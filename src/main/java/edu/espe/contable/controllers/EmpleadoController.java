package edu.espe.contable.controllers;

import edu.espe.contable.entities.Empleado;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public List<Empleado> empleados(){
        return empleadoRepository.findAll();
    }

    @GetMapping("/empleados/{cedula}")
    public ResponseEntity<Empleado> getempleado(@PathVariable String cedula){
        Empleado empleado = empleadoRepository.findById(cedula)
                .orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/empleados")
    public Empleado addempleado(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/empleados/{cedula}")
    public ResponseEntity<Empleado> updateempleado(@PathVariable String cedula, @RequestBody Empleado empleadoRequest){
        Empleado empleado = empleadoRepository.findById(cedula).orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));

        if (empleado != null){
            empleado.setNombre(empleadoRequest.getNombre());
            empleado.setFechaIngreso(empleadoRequest.getFechaIngreso());
            empleado.setSueldo(empleadoRequest.getSueldo());

            Empleado empleadoUpdated = empleadoRepository.save(empleado);
            return ResponseEntity.ok(empleadoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/empleados/{cedula}")
    public ResponseEntity<Map<String, Boolean>> deleteempleado(@PathVariable String cedula){
        Empleado empleado = empleadoRepository.findById(cedula).orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));

        empleadoRepository.delete(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
