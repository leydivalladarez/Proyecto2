package edu.espe.contable.repository;

import edu.espe.contable.entities.TipoActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoActivoRepository extends JpaRepository<TipoActivo, Long> {
    List<TipoActivo> findByNombreContainingIgnoreCase(String nombre);
}
