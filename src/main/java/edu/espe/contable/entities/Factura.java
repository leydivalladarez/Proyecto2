package edu.espe.contable.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "FACTURA")
public class Factura {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CIUDAD_CODIGO", nullable = false)
    private Ciudad ciudadCodigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente clienteId;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<FacturaDetalle> facturaDetalles = new LinkedHashSet<>();

}