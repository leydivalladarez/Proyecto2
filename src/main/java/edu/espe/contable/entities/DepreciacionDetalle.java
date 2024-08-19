package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "DEPRECIACION_DETALLE")
public class DepreciacionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACTIVO_ID", nullable = false)
    private Activo activo;

    @NotNull
    @Column(name = "PERIODO_DEPRECIACION", nullable = false)
    private Long periodoDepreciacion;

    @NotNull
    @Column(name = "VALOR_DEPRECIACION", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorDepreciacion;

    @ManyToOne()
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "DEPRECIACION_NUMERO", nullable = false)
    private Depreciacion depreciacion;

}