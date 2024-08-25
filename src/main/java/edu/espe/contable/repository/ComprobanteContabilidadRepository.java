package edu.espe.contable.repository;

import edu.espe.contable.entities.ComprobanteContabilidad;
import edu.espe.contable.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComprobanteContabilidadRepository extends JpaRepository<ComprobanteContabilidad, Long> {
    List<ComprobanteContabilidad> findByNumeroOrFecha(Long numero, LocalDate fecha);
}
