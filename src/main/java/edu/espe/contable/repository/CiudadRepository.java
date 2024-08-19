package edu.espe.contable.repository;

import edu.espe.contable.entities.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);
}
