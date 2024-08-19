package edu.espe.contable.repository;

import edu.espe.contable.entities.Depreciacion;
import edu.espe.contable.entities.DepreciacionDetalle;
import edu.espe.contable.entities.Factura;
import edu.espe.contable.entities.FacturaDetalle;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DepreciacionDetalleRepository extends JpaRepository<DepreciacionDetalle, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM DepreciacionDetalle dd WHERE dd.depreciacion = :depreciacion")
    void deleteByDepreciacion(Depreciacion depreciacion);

    List<DepreciacionDetalle> findByDepreciacion_FechaBetween( LocalDate depreciacion_fecha,  LocalDate depreciacion_fecha2);
}
