package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ARTICULO")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "PRECIO", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @JsonIgnore
    @OneToMany(mappedBy = "articulo")
    private Set<FacturaDetalle> facturaDetalles = new LinkedHashSet<>();

}