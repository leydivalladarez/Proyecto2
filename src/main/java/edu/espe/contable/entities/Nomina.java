package edu.espe.contable.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "NOMINA")
public class Nomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @ManyToOne()
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.RESTRICT)
    @JoinColumn(name = "EMPLEADO_CEDULA", nullable = false)
    private Empleado empleado;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @OneToMany(mappedBy = "nomina", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<NominaDetalle> nominaDetalles = new LinkedHashSet<>();
}