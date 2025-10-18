package py.una.pol.sd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Juego {

  @Id
  private Integer id;
  private String nombre;
  private Float porcentaje;

  public Juego() {}

  public Juego(Integer id, String nombre, Float porcentaje) {
    this.id = id;
    this.nombre = nombre;
    this.porcentaje = porcentaje;
  }

  @Override
  public String toString() {
    return String.format(
        "Juego[id=%d, nombre='%s', porcentaje='%f']",
        id, nombre, porcentaje);
  }

public Integer getId() {
    return id;
}

public String getNombre() {
    return nombre;
}

public Float getPorcentaje() {
    return porcentaje;
}

public void setId(Integer id) {
    this.id = id;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setPorcentaje(Float porcentaje) {
    this.porcentaje = porcentaje;
}

 
}
