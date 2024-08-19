package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TIPO_ACTIVO")
public class TipoActivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    private Long codigo;

    @Size(max = 100)
    @NotNull
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoActivo")
    private Set<Activo> activos = new LinkedHashSet<>();

}