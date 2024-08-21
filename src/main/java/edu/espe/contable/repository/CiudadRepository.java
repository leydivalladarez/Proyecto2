package edu.espe.contable.repository;

import edu.espe.contable.dtos.CiudadVentasDTO;
import edu.espe.contable.entities.Ciudad;
import edu.espe.contable.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT new edu.espe.contable.dtos.CiudadVentasDTO(c.codigo, c.nombre, SUM(d.precio*d.cantidad)) " +
            "FROM Ciudad c " +
            "JOIN c.facturas f " +
            "JOIN f.facturaDetalles d " +
            "WHERE f.fecha BETWEEN :startDate AND :endDate " +
            "GROUP BY c.codigo, c.nombre")
    List<CiudadVentasDTO> ventasPorCiudadesEnRangoFechas(@Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

    @Query("SELECT new edu.espe.contable.dtos.CiudadVentasDTO(c.codigo, c.nombre, SUM(d.precio*d.cantidad)) " +
            "FROM Ciudad c " +
            "JOIN c.facturas f " +
            "JOIN f.facturaDetalles d " +
            "GROUP BY c.codigo, c.nombre")
    List<CiudadVentasDTO> ventasPorCiudades();
}
