package edu.espe.contable.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.espe.contable.Views;
import edu.espe.contable.entities.Cuenta;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    @JsonView(Views.Simple.class)
    public List<Cuenta> cuentas(@RequestParam(required = false) String buscar) {
        Long codigo = null;
        if (buscar != null) {
            try {
                codigo = Long.parseLong(buscar);
            } catch (NumberFormatException e) {
                // Log error si es necesario
            }
        }

        List<Cuenta> cuentas;

        if (buscar == null || buscar.isEmpty()) {
            cuentas = cuentaRepository.findAll();
        } else {
            cuentas = cuentaRepository.findByCodigoOrNombreContainingIgnoreCase(codigo, buscar);
        }

        // Si hay resultados, reconstituir la jerarquía
        if (!cuentas.isEmpty()) {
            // Asegurarse de que todas las cuentas padre estén incluidas
            Set<Long> parentIds = new HashSet<>();
            for (Cuenta cuenta : cuentas) {
                Cuenta parent = cuenta.getCuentaPadre();
                while (parent != null) {
                    parentIds.add(parent.getCodigo());
                    parent = parent.getCuentaPadre();
                }
            }

            if (!parentIds.isEmpty()) {
                List<Cuenta> parents = cuentaRepository.findAllById(parentIds);
                cuentas.addAll(parents);
            }

            // Eliminar duplicados y reconstituir la jerarquía
            cuentas = new ArrayList<>(new HashSet<>(cuentas));
            cuentas.sort(Comparator.comparing(Cuenta::getCodigo)); // Ordenar por código o según la jerarquía que necesites
        }

        return cuentas;
    }

    @GetMapping("/cuentas/jerarquico")
    @JsonView(Views.Hierarchical.class)
    public List<Cuenta> cuentasJerarquico(
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String tipo
    ) {
        Long codigo = null;
        if (buscar != null) {
            try {
                codigo = Long.parseLong(buscar);
            } catch (NumberFormatException e) {
                // Log error si es necesario
            }
        }

        List<Cuenta> cuentas;

        if (tipo != null && !tipo.isEmpty() && tipo.compareTo("resultados") == 0) {
            cuentas = cuentaRepository.findParentWithChildrenTipoResultados();
        } else if (buscar == null || buscar.isEmpty()) {
            cuentas = cuentaRepository.findByCuentaPadreIsNullOrderByCodigoAsc();
        } else {
            cuentas = cuentaRepository.findParentWithChildrenByNombre(buscar);
            cuentas = cuentas.stream()
                    .filter(cuenta -> cuenta.getCuentaPadre() == null)
                    .collect(Collectors.toList());
        }
        return cuentas;
    }


    @GetMapping("/cuentas/{codigo}")
    @JsonView(Views.Simple.class)
    public ResponseEntity<Cuenta> getCuenta(@PathVariable Long codigo){
        Cuenta cuenta = cuentaRepository.findById(codigo)
                .orElseThrow(()->new ResourceNotFoundException("Cuenta no encontrada"));
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping("/cuentas")
    public Cuenta addCuenta(@RequestBody Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    @PutMapping("/cuentas/{codigo}")
    @JsonView(Views.Simple.class)
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long codigo, @RequestBody Cuenta cuentaRequest){
        Cuenta cuenta = cuentaRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Cuenta no encontrada"));

        if (cuenta != null){
            cuenta.setNombre(cuentaRequest.getNombre());
            cuenta.setTipoCuenta(cuentaRequest.getTipoCuenta());
            cuenta.setCuentaPadre(cuentaRequest.getCuentaPadre());

            Cuenta cuentaUpdated = cuentaRepository.save(cuenta);
            return ResponseEntity.ok(cuentaUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cuentas/{codigo}")
    public ResponseEntity<Map<String, Boolean>> deleteCuenta(@PathVariable Long codigo){
        Cuenta cuenta = cuentaRepository.findById(codigo).orElseThrow(()->new ResourceNotFoundException("Cuenta no encontrada"));

        cuentaRepository.delete(cuenta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
