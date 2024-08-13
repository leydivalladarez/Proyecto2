package edu.espe.contable.entities;

import jakarta.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

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

}