package com.github.app.domain.valueobject;

import core.lib.ValueObject;

import java.util.Objects;

public final class Sku extends ValueObject {

  private String valeur;

  public Sku(String valeur) {
    setSku(valeur);
  }

  public String valeur() {
    return valeur;
  }

  private void setSku(String valeur) {
    this.verifieArgumentNonVide(valeur, "La valeur du sku ne peut etre vide.");
    this.valeur = valeur;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Sku sku = (Sku) o;
    return valeur.equals(sku.valeur);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(valeur);
  }
}
