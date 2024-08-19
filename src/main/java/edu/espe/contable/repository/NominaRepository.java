package edu.espe.contable.repository;

import edu.espe.contable.dtos.ValorAPagarDTO;
import edu.espe.contable.entities.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> {
    @Transactional
    @Modifying
    @Query(value = "SELECT e.nombre AS nombreEmpleado, " +
            "SUM(CASE WHEN m.tipo = 'ingreso' THEN d.valor " +
            "WHEN m.tipo = 'egreso' THEN -d.valor ELSE 0 END) AS valorTotal " +
            "FROM nomina n " +
            "JOIN empleado e ON n.empleado_id = e.id " +
            "JOIN NOMINA_DETALLE d ON n.NUMERO = d.NOMINA_NUMERO " +
            "JOIN motivo m ON d.MOTIVO_CODIGO = m.CODIGO " +
            "GROUP BY e.nombre", nativeQuery = true)
    List<ValorAPagarProjection> findValoresAPagar();
}

