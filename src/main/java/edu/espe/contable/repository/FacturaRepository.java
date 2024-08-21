package edu.espe.contable.repository;

import edu.espe.contable.entities.Cliente;
import edu.espe.contable.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByIdOrCliente_NombreContainingIgnoreCase(Long id, String nombre);
}
