package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonView;
import edu.espe.contable.Views;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMPROBANTE_CONTABILIDAD")
public class ComprobanteContabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Long numero;

    @ColumnDefault("sysdate")
    @Column(name = "FECHA", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private LocalDate fecha;

    @Column(name = "OBSERVACIONES")
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private String observaciones;

    @OneToMany(mappedBy = "comprobanteContabilidad")
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Set<ComprobanteContabilidadDetalle> comprobanteContabilidadDetalles = new LinkedHashSet<>();

}