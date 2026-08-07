package com.github.smgueye.app.persistenceadapter;

public record AttributJson(String type, String texte, Double numerique, Boolean booleen) {

  public static AttributJson texte(String valeur) {
    return new AttributJson("TEXTE", valeur, null, null);
  }

  public static AttributJson numerique(double valeur) {
    return new AttributJson("NUMERIQUE", null, valeur, null);
  }

  public static AttributJson booleen(boolean valeur) {
    return new AttributJson("BOOLEEN", null, null, valeur);
  }
}
