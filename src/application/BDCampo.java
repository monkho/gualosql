package application;

import java.util.List;
import java.util.ArrayList;

public class BDCampo {
  private String nombreCampo;
  private String nombreTipo;
  private List<String> valores;

  public BDCampo() {
    nombreCampo = "";
    nombreTipo = "";
    valores = new ArrayList<>();
  }

  public BDCampo(String nombreCampo, String nombreTipo) {
    this.nombreCampo = nombreCampo;
    this.nombreTipo = nombreTipo;
    valores = new ArrayList<>();
  }

  public void setNombreCampo(String nombreCampo) {
    this.nombreCampo = nombreCampo;
  }

  public String getNombreCampo() {
    return nombreCampo;
  }

  public void setNombreTipo(String nombreTipo) {
    this.nombreTipo = nombreTipo;
  }

  public String getNombreTipo() {
    return nombreTipo;
  }

  public void addValor(String valor) {
    this.valores.add(valor);
  }

  public void setValor(String oldValor, String newValor) {
    System.out.println("En el campo : " + nombreCampo);
    System.out.println("Antiguo: " + valores.indexOf(oldValor) + ", Nuevo: " + newValor);
    // valores.set(valores.indexOf(oldValor), newValor);
  }

  public List<String> getValores() {
    return valores;
  }

  public String toString() {
    return "\tCampo: " + nombreCampo + ", Tipo: " + nombreTipo + "\n\t\t\tValores: " + valores.toString() + '\n';
  }
}
