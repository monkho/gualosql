package application;

import java.util.List;
import java.util.ArrayList;

public class BDTabla {
  private String nombreTabla;
  private List<BDCampo> campos;

  public BDTabla() {
    nombreTabla = "";
    campos = new ArrayList<BDCampo>();
  }

  public BDTabla(String nombreTabla) {
    this.nombreTabla = nombreTabla;
    campos = new ArrayList<>();
  }

  public void setNombreTabla(String nombreTabla) {
    this.nombreTabla = nombreTabla;
  }

  public String getNombreTabla() {
    return nombreTabla;
  }

  public void addCampo(BDCampo campo) {
    campos.add(campo);
  }

  public void addCampos(List<BDCampo> campos) {
    this.campos = campos;
  }

  public List<BDCampo> getCampos() {
    return campos;
  }

  public String getIdTabla() {
    String id = "";
    for (BDCampo c : campos) {
      if (c.getNombreCampo().contains("id_") && !c.getNombreCampo().contains("fk_")) {
        id = c.getNombreCampo();
      }
    }
    return id;
  }

  public String getForeingKey() {
    String fk = "";
    for (BDCampo c : campos) {
      if (c.getNombreCampo().contains("fk_")) {
        fk = c.getNombreCampo();
      }
    }
    return fk;
  }

  public String toString() {
    String s = "\tTabla: " + nombreTabla + '\n';
    for (BDCampo campo : campos) {
      if (campo != null)
        s += '\t' + campo.toString();
    }
    return s;
  }
}
