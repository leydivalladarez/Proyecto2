package edu.espe.contable.repository;

import edu.espe.contable.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.ListCrudRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
