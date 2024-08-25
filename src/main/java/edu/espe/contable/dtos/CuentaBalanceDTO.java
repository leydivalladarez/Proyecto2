package edu.espe.contable.dtos;

import edu.espe.contable.entities.Cuenta;
import edu.espe.contable.entities.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CuentaBalanceDTO {
    private Long codigo;
    private String nombre;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldoDebe;
    private BigDecimal saldoHaber;
    private BigDecimal balance;
    private Set<Cuenta> cuentas;

    public CuentaBalanceDTO(Long codigo, String nombre, TipoCuenta tipoCuenta, BigDecimal saldoDebe, BigDecimal saldoHaber, Set<Cuenta> cuentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoCuenta = tipoCuenta;
        this.saldoDebe = saldoDebe;
        this.saldoHaber = saldoHaber;
        this.balance = saldoDebe.subtract(saldoHaber);
        this.cuentas = cuentas;
    }
}

