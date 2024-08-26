package edu.espe.contable.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ROL_ID", nullable = false)
    private Long rolId;

    @Column(name = "USERNAME", nullable = false, length = 100)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @ColumnDefault("'ACTIVE'")
    @Column(name = "ACCOUNT_STATUS", nullable = false, length = 20)
    private String accountStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updatedAt;

}