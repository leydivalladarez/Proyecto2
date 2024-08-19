package edu.espe.contable.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ValorAPagarDTO {
    private String nombreEmpleado;
    private BigDecimal valorTotal;

    public ValorAPagarDTO(String nombreEmpleado, BigDecimal valorTotal) {
        this.nombreEmpleado = nombreEmpleado;
        this.valorTotal = valorTotal;
    }
}