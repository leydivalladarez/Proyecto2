package edu.espe.contable.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CiudadVentasDTO {
    private Long codigo;
    private String nombre;
    private BigDecimal ventasTotales;

    public CiudadVentasDTO(Long codigo,String nombre, BigDecimal ventasTotales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ventasTotales = ventasTotales;
    }

}
