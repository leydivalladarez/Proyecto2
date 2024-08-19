package edu.espe.contable.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DEPRECIACION")
public class Depreciacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Size(max = 250)
    @Column(name = "OBSERVACIONES", length = 250)
    private String observaciones;

    @Size(max = 150)
    @NotNull
    @Column(name = "RESPONSABLE", nullable = false, length = 150)
    private String responsable;

    @OneToMany(mappedBy = "depreciacion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<DepreciacionDetalle> depreciacionDetalles = new LinkedHashSet<>();

}