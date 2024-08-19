package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ACTIVO")
public class Activo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;

    @NotNull
    @Column(name = "PERIODOS_DEPRECIACION_TOTAL", nullable = false)
    private Long periodosDepreciacionTotal;

    @NotNull
    @Column(name = "VALOR_COMPRA", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorCompra;

    @NotNull
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_ACTIVO_CODIGO", nullable = false)
    private TipoActivo tipoActivo;

    @JsonIgnore
    @OneToMany(mappedBy = "activo")
    private Set<DepreciacionDetalle> depreciacionDetalles = new LinkedHashSet<>();

}