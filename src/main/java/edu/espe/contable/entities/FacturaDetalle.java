package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "FACTURA_DETALLE")
public class FacturaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ARTICULO_CODIGO", nullable = false)
    private Articulo articulo;

    @ColumnDefault("1")
    @Column(name = "CANTIDAD", nullable = false)
    private Long cantidad;

    @Column(name = "PRECIO", nullable = false, precision = 8, scale = 2)
    private BigDecimal precio;

    @JsonIgnore
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FACTURA_ID", nullable = false)
    private Factura factura;

}