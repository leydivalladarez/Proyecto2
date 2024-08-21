package edu.espe.contable.repository;

import edu.espe.contable.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByCedulaContainingOrNombreContainingIgnoreCase(String cedula, String nombre);
}
