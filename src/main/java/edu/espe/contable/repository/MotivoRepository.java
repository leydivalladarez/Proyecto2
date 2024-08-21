package edu.espe.contable.repository;

import edu.espe.contable.entities.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {
    List<Motivo> findByNombreContainingIgnoreCaseOrTipoContainingIgnoreCase(String nombre, String tipo);
}
