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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public List<Empleado> empleados(@RequestParam(required = false) String buscar){
        if(buscar == null || buscar.isEmpty()){
            return empleadoRepository.findAll();
        }else{
            return empleadoRepository.findByCedulaContainingOrNombreContainingIgnoreCase(buscar, buscar);
        }
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getempleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/empleados")
    public Empleado addempleado(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> updateempleado(@PathVariable Long id, @RequestBody Empleado empleadoRequest){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));

        if (empleado != null){
            empleado.setCedula(empleadoRequest.getCedula());
            empleado.setNombre(empleadoRequest.getNombre());
            empleado.setFechaIngreso(empleadoRequest.getFechaIngreso());
            empleado.setSueldo(empleadoRequest.getSueldo());

            Empleado empleadoUpdated = empleadoRepository.save(empleado);
            return ResponseEntity.ok(empleadoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteempleado(@PathVariable Long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Empleado no encontrado"));

        empleadoRepository.delete(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
