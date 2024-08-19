package edu.espe.contable.dtos;

import edu.espe.contable.entities.Activo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepreciacionReporteDTO {
    private Activo activo;
    private Double valorDepreciado;

    public DepreciacionReporteDTO(Activo activo, Double valorDepreciado) {
        this.activo = activo;
        this.valorDepreciado = valorDepreciado;
    }
}

