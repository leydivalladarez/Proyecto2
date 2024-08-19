package edu.espe.contable.repository;

import edu.espe.contable.entities.Depreciacion;
import edu.espe.contable.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DepreciacionRepository extends JpaRepository<Depreciacion, Long> {
    List<Depreciacion> findDepreciacionByNumeroOrFechaOrResponsableEndingWithIgnoreCase(Long numero, Date fecha, String responsable);
}
