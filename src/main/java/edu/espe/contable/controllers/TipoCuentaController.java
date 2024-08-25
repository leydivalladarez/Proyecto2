package edu.espe.contable.controllers;

import edu.espe.contable.entities.TipoCuenta;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class TipoCuentaController {
    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;

    @GetMapping("/tipoCuentas")
    public List<TipoCuenta> tipoCuentas(@RequestParam(required = false) String buscar){
        Long codigo = null;
        if(buscar != null){
            try {
                codigo = Long.parseLong(buscar);
            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
            }
        }
        if(buscar == null || buscar.isEmpty()){
            return tipoCuentaRepository.findAll();
        }else {
            return tipoCuentaRepository.findByCodigoOrNombreContainingIgnoreCase(codigo, buscar);
        }
    }

    @GetMapping("/tipoCuentas/{codigo}")
    public ResponseEntity<TipoCuenta> getTipoCuenta(@PathVariable Long codigo){
        TipoCuenta tipoCuenta = tipoCuentaRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("TipoCuenta no encontrada"));
        return ResponseEntity.ok(tipoCuenta);
    }

    @PostMapping("/tipoCuentas")
    public TipoCuenta addTipoCuenta(@RequestBody TipoCuenta tipoCuenta){
        return tipoCuentaRepository.save(tipoCuenta);
    }

    @PutMapping("/tipoCuentas/{codigo}")
    public ResponseEntity<TipoCuenta> updateTipoCuenta(@PathVariable Long codigo, @RequestBody TipoCuenta tipoCuentaRequest){
        TipoCuenta tipoCuenta = tipoCuentaRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("TipoCuenta no encontrada"));

        if (tipoCuenta != null){
            tipoCuenta.setNombre(tipoCuentaRequest.getNombre());

            TipoCuenta tipoCuentaUpdated = tipoCuentaRepository.save(tipoCuenta);
            return ResponseEntity.ok(tipoCuentaUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tipoCuentas/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deleteTipoCuenta(@PathVariable Long codigo){
        TipoCuenta tipoCuenta = tipoCuentaRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("TipoCuenta no encontrada"));

        tipoCuentaRepository.delete(tipoCuenta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
