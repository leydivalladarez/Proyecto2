package edu.espe.contable.repository;

import edu.espe.contable.entities.Activo;
import edu.espe.contable.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    Usuario findUsuarioByUsername(String username);
}
