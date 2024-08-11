package edu.espe.contable.repository;

import edu.espe.contable.entities.Factura;
import edu.espe.contable.entities.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM FacturaDetalle fd WHERE fd.factura = :factura")
    void deleteByFactura(Factura factura);
}
