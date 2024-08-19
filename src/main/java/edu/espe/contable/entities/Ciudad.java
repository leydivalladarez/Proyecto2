package edu.espe.contable.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CIUDAD")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudad")
    private Set<Factura> facturas = new LinkedHashSet<>();

    public BigDecimal obtenerVentasTotales() {
        BigDecimal totalVentas = BigDecimal.ZERO;

        for (Factura factura : facturas) {
            for (FacturaDetalle detalle : factura.getFacturaDetalles()) {
                BigDecimal precio = detalle.getPrecio();
                BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());
                totalVentas = totalVentas.add(precio.multiply(cantidad));
            }
        }

        return totalVentas;
    }
}