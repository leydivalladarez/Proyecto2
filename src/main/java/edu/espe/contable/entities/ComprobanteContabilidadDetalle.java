package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.espe.contable.Views;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "COMPROBANTE_CONTABILIDAD_DETALLE")
public class ComprobanteContabilidadDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CUENTA_CODIGO", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Cuenta cuenta;

    @Column(name = "DEBE", precision = 8, scale = 2)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private BigDecimal debe;

    @Column(name = "HABER", precision = 8, scale = 2)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private BigDecimal haber;

    @JsonIgnore
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "COMPROBANTE_CONTABILIDAD_NUMERO", nullable = false)
    private ComprobanteContabilidad comprobanteContabilidad;

    public BigDecimal getDebe() {
        return debe != null ? debe : BigDecimal.ZERO;
    }

    public BigDecimal getHaber() {
        return haber != null ? haber : BigDecimal.ZERO;
    }
}