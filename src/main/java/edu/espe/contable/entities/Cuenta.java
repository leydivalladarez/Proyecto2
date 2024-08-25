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
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CUENTA")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Long codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private String nombre;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TIPO_CUENTA_CODIGO", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private TipoCuenta tipoCuenta;


    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CUENTA_PADRE_CODIGO")
    @JsonView(Views.Simple.class)
    private Cuenta cuentaPadre;

    @JsonIgnore
    @OneToMany(mappedBy = "cuenta")
    private Set<ComprobanteContabilidadDetalle> comprobanteContabilidadDetalles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cuentaPadre")
    @JsonView(Views.Hierarchical.class)
    private Set<Cuenta> cuentas = new LinkedHashSet<>();

    // Método para calcular el saldo debe en tiempo real
    public BigDecimal getSaldoDebe() {
        return comprobanteContabilidadDetalles.stream()
                .map(ComprobanteContabilidadDetalle::getDebe)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Método para calcular el saldo haber en tiempo real
    public BigDecimal getSaldoHaber() {
        return comprobanteContabilidadDetalles.stream()
                .map(ComprobanteContabilidadDetalle::getHaber)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}