package py.una.pol.sd.model;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CuentaBancaria {

    @Id
    private Integer idbanco;
    private String denominacion;
    private BigInteger nrocuenta;
    private BigInteger saldo;
    private Boolean cuentaPadre;

    // El constructor por defecto es necesario para JPA y Jackson
    public CuentaBancaria() {}

    // Constructor con parámetros (opcional pero buena práctica)
    public CuentaBancaria(Integer idbanco, String denominacion, BigInteger nrocuenta, BigInteger saldo, Boolean cuentaPadre) {
        this.idbanco = idbanco;
        this.denominacion = denominacion;
        this.nrocuenta = nrocuenta;
        this.saldo = saldo;
        this.cuentaPadre = cuentaPadre;
    }

    // --- GETTERS (Estos estaban bien) ---
    public Integer getIdBanco() {
        return idbanco;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public BigInteger getNrocuenta() {
        return nrocuenta;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public Boolean getCuentaPadre() {
        return cuentaPadre;
    }

    // --- SETTERS (Aquí estaban los errores) ---
    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setNrocuenta(BigInteger nrocuenta) {
        this.nrocuenta = nrocuenta;
    }

    public void setSaldo(BigInteger saldo) {
        this.saldo = saldo;
    }

    public void setCuentapadre(Boolean cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }


    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "idbanco=" + idbanco +
                ", denominacion='" + denominacion + '\'' +
                ", nrocuenta=" + nrocuenta +
                ", saldo=" + saldo +
                ", cuentaPadre=" + cuentaPadre +
                '}';
    }
}