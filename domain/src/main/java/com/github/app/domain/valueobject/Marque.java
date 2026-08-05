package com.github.app.domain.valueobject;

import core.lib.ValueObject;

import java.util.Objects;

public final class Marque extends ValueObject {

  private final String valeur;

  public Marque(String valeur) {
    verifieArgumentNonVide(valeur,"La marque du produit est requise.");

    this.valeur = valeur.strip();
  }

  public String valeur() {
    return valeur;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Marque marque = (Marque) o;
    return Objects.equals(valeur, marque.valeur);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(valeur);
  }
}
