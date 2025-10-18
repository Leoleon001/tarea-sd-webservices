package py.una.pol.sd.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {

  @Id
  private Integer cedula;
  private String nombre;
  private String apellido;
  private BigInteger dineroapostado;
  private BigInteger dineroganado;

  protected Persona() {}

  public Persona(Integer cedula, String nombre, String apellido, BigInteger dineroapostado, BigInteger dineroganado) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.apellido = apellido;
    this.dineroapostado = dineroapostado;
    this.dineroganado = dineroganado;
  }

  @Override
  public String toString() {
    return String.format(
        "Persona[cedula=%d, nombre='%s', apellido='%s , dineroapostado='%bd, dineroganado='%bd']",
        cedula, nombre, apellido, dineroapostado, dineroganado);
  }

public Integer getCedula() {
    return cedula;
}

public String getNombre() {
    return nombre;
}

public String getApellido() {
    return apellido;
}

public BigInteger getDineroapostado() {
    return dineroapostado;
}

public BigInteger getDineroganado() {
    return dineroganado;
}

public void setCedula(Integer cedula) {
    this.cedula = cedula;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public void setDineroapostado(BigInteger dineroapostado) {
    this.dineroapostado = dineroapostado; 
  
}
public void setDineroganado(BigInteger dineroganado) {
    this.dineroganado = dineroganado; 
}
 
}
