package edu.espe.contable.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "NOMINA_DETALLE")
public class NominaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "VALOR", nullable = false, precision = 8, scale = 2)
    private BigDecimal valor;

    @ManyToOne()
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NOMINA_NUMERO", nullable = false)
    private Nomina nomina;

    @ManyToOne()
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MOTIVO_CODIGO", nullable = false)
    private Motivo motivo;

}