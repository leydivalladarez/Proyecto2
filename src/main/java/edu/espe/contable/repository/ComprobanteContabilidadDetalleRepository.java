package edu.espe.contable.repository;

import edu.espe.contable.entities.ComprobanteContabilidad;
import edu.espe.contable.entities.ComprobanteContabilidadDetalle;
import edu.espe.contable.entities.Factura;
import edu.espe.contable.entities.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComprobanteContabilidadDetalleRepository extends JpaRepository<ComprobanteContabilidadDetalle, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ComprobanteContabilidadDetalle ccd WHERE ccd.comprobanteContabilidad = :comprobanteContabilidad")
    void deleteByComprobanteContabilidad(ComprobanteContabilidad comprobanteContabilidad);

    @Query("SELECT d FROM ComprobanteContabilidadDetalle d " +
            "JOIN d.comprobanteContabilidad c " +
            "WHERE (:fechaInicio IS NULL OR c.fecha >= :fechaInicio) " +
            "AND (:fechaFin IS NULL OR c.fecha <= :fechaFin)")
    List<ComprobanteContabilidadDetalle> findByComprobanteFechaBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT d FROM ComprobanteContabilidadDetalle d " +
            "JOIN d.comprobanteContabilidad c " +
            "JOIN d.cuenta cu " +
            "WHERE LOWER(cu.tipoCuenta.nombre) LIKE LOWER('%ingreso%') " +
            "OR LOWER(cu.tipoCuenta.nombre) LIKE LOWER('%egreso%')")
    List<ComprobanteContabilidadDetalle> findByTipoResultadosComprobante();

    @Query("SELECT d FROM ComprobanteContabilidadDetalle d " +
            "JOIN d.comprobanteContabilidad c " +
            "JOIN d.cuenta cu " +
            "WHERE ((:fechaInicio IS NULL OR c.fecha >= :fechaInicio) " +
            "AND (:fechaFin IS NULL OR c.fecha <= :fechaFin)) " +
            "AND (LOWER(cu.tipoCuenta.nombre) LIKE LOWER('%ingreso%') " +
            "OR LOWER(cu.tipoCuenta.nombre) LIKE LOWER('%egreso%'))")
    List<ComprobanteContabilidadDetalle> findByTipoResultadosComprobanteFechaBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}
