package edu.espe.contable.repository;

import edu.espe.contable.entities.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Long> {
    List<TipoCuenta> findByCodigoOrNombreContainingIgnoreCase(Long codigo, String nombre);

    TipoCuenta findByNombre(String nombre);
}
