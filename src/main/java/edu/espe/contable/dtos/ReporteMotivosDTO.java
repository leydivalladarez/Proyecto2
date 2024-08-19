package edu.espe.contable.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
public class ReporteMotivosDTO {
    private Long empleadoId;
    private String nombreEmpleado;
    private Map<Long, BigDecimal> motivosPorEmpleado;

    public ReporteMotivosDTO(Long empleadoId, String nombreEmpleado, Map<Long, BigDecimal> motivosPorEmpleado) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.motivosPorEmpleado = motivosPorEmpleado;
    }

    // Getters y setters
}
