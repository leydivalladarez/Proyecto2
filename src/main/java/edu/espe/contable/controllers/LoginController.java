package edu.espe.contable.controllers;

import edu.espe.contable.dtos.LoginRequest;
import edu.espe.contable.dtos.LoginResponse;
import edu.espe.contable.entities.Usuario;
import edu.espe.contable.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*@GetMapping("/login")
    public String login() {
        return "login"; // Retorna la vista de inicio de sesión
    }*/

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Retorna la vista de dashboard
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> postLogin(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        System.out.println("Received login request for username: " + username);
        System.out.println("Password provided: " + password);

        Usuario user = usuarioRepository.findUsuarioByUsername(username);

        if (user != null) {
            System.out.println("User found in database: " + user.getUsername());
            if (user.getPassword().equals(password)) {
                System.out.println("Password matches for user: " + username);
                // Respuesta exitosa
                LoginResponse response = new LoginResponse("Login successful", true);
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Password does not match for user: " + username);
                // Respuesta de error
                LoginResponse response = new LoginResponse("Usuario o contraseña incorrectos", false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            System.out.println("User not found in database: " + username);
            // Respuesta de error
            LoginResponse response = new LoginResponse("Usuario o contraseña incorrectos", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
