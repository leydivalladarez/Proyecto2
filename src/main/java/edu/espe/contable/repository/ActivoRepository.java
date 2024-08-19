package edu.espe.contable.repository;

import edu.espe.contable.entities.Activo;
import edu.espe.contable.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivoRepository extends JpaRepository<Activo, Long> {
    List<Activo> findByNombreContainingIgnoreCase(String nombre);
}
