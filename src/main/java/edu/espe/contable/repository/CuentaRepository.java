package edu.espe.contable.repository;

import edu.espe.contable.entities.Cliente;
import edu.espe.contable.entities.Cuenta;
import edu.espe.contable.entities.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByCodigoOrNombreContainingIgnoreCase(Long codigo, String nombre);

    List<Cuenta> findByCuentaPadreIsNullOrderByCodigoAsc();

    @Query("SELECT DISTINCT c FROM Cuenta c " +
            "LEFT JOIN FETCH c.cuentas h " +
            "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
            "OR (LOWER(h.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND h IS NOT NULL) " +
            "order by c.codigo ASC")
    List<Cuenta> findParentWithChildrenByNombre(@Param("nombre") String nombre);

    @Query("SELECT DISTINCT c FROM Cuenta c " +
            "LEFT JOIN FETCH c.cuentas h " +
            "JOIN c.tipoCuenta tc " +
            "WHERE LOWER(tc.nombre) LIKE LOWER('%ingreso%') " +
            "OR LOWER(tc.nombre) LIKE LOWER('%egreso%') " +
            "OR LOWER(h.tipoCuenta.nombre) LIKE LOWER('%ingreso%') " +
            "OR LOWER(h.tipoCuenta.nombre) LIKE LOWER('%egreso%') " +
            "order by c.codigo ASC")
    List<Cuenta> findParentWithChildrenTipoResultados();

    List<Cuenta> findByTipoCuentaIn(List<TipoCuenta> tiposCuenta);
}
