package edu.espe.contable.dtos;

import edu.espe.contable.entities.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CuentaResultadoDTO {
    private Long codigo;
    private String nombre;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldoDebe;
    private BigDecimal saldoHaber;
    private BigDecimal balance;
    private List<CuentaResultadoDTO> cuentas;

    public CuentaResultadoDTO(Long codigo, String nombre, TipoCuenta tipoCuenta, BigDecimal saldoDebe, BigDecimal saldoHaber, List<CuentaResultadoDTO> cuentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoCuenta = tipoCuenta;
        this.saldoDebe = saldoDebe;
        this.saldoHaber = saldoHaber;
        this.balance = (tipoCuenta.getNombre().toLowerCase().contains("ingreso") ? saldoHaber.subtract(saldoDebe) : saldoDebe.subtract(saldoHaber) ) ;
        this.cuentas = cuentas;
    }
}

