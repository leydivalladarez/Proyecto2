package edu.espe.contable.repository;

import edu.espe.contable.entities.Factura;
import edu.espe.contable.entities.Nomina;
import edu.espe.contable.entities.NominaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NominaDetalleRepository extends JpaRepository<NominaDetalle, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM NominaDetalle nd WHERE nd.nomina = :nomina")
    void deleteByNomina(Nomina nomina);
}
