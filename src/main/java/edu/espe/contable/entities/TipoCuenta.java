package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.espe.contable.Views;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TIPO_CUENTA")
public class TipoCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private Long codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    @JsonView({Views.Simple.class, Views.Hierarchical.class})
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoCuenta")
    private Set<Cuenta> cuentas = new LinkedHashSet<>();

}