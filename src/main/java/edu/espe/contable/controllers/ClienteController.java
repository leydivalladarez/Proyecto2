package edu.espe.contable.controllers;

import edu.espe.contable.entities.Cliente;
import edu.espe.contable.exception.ResourceNotFoundException;
import edu.espe.contable.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public String home(){
        return "Hola Mundo, desde API de Clientes";
    }

    @GetMapping("/clientes")
    public List<Cliente> clientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado"));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public Cliente addCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado"));

        if (cliente != null){
            cliente.setRuc(clienteRequest.getRuc());
            cliente.setNombre(clienteRequest.getNombre());
            cliente.setDireccion(clienteRequest.getDireccion());

            Cliente clienteUpdated = clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //@RequestMapping(path = "/cliente/{id}/", method = RequestMethod.DELETE)
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
