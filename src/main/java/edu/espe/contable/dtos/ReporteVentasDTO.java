package edu.espe.contable.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
public class ReporteVentasDTO {
    private Long codigoArticulo;
    private String nombreArticulo;
    private Map<Long, BigDecimal> ventasPorCliente;

    public ReporteVentasDTO(Long codigoArticulo, String nombreArticulo, Map<Long, BigDecimal> ventasPorCliente) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.ventasPorCliente = ventasPorCliente;
    }
}
