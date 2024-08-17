package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MOTIVO")
public class Motivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TIPO", length = 20)
    @Pattern(regexp = "ingreso|egreso", message = "El tipo debe ser 'ingreso' o 'egreso'")
    private String tipo;

    @JsonIgnore
    @OneToMany(mappedBy = "motivo")
    private Set<NominaDetalle> nominaDetalles = new LinkedHashSet<>();

}