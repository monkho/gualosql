package application;

import java.util.List;
import java.util.ArrayList;

public class BaseDatos {
  private String nombreDB;
  private List<BDTabla> tablas;

  public BaseDatos() {
    nombreDB = "";
    tablas = new ArrayList<BDTabla>();
  }

  public BaseDatos(String nombreDB) {
    this.nombreDB = nombreDB;
    tablas = new ArrayList<>();
  }

  public void setNombreDB(String nombreDB) {
    this.nombreDB = nombreDB;
  }

  public String getNombreDB() {
    return nombreDB;
  }

  public boolean addTabla(BDTabla tabla) {
    if (tablas.contains(tabla)) {
      return true;
    }
    tablas.add(tabla);
    return false;
  }

  public void removeTabla(BDTabla t) {
    tablas.remove(t);
  }

  public List<BDTabla> getTablas() {
    return tablas;
  }

  public BDTabla getTabla(String nombre) {
    for (BDTabla t : tablas) {
      if (t.getNombreTabla() == nombre) {
        return t;
      }
    }
    return null;
  }

  public String toString() {
    String s = "Base de datos: " + nombreDB + "\n";

    for (BDTabla tabla : tablas) {
      if (tabla != null)
        s += tabla.toString();
    }
    return s;
  }

}