package com.github.app.domain.valueobject;

import java.util.Objects;

public class Marque {

  private final String valeur;

  public Marque(String valeur) {
    Objects.requireNonNull(valeur);
    assert !valeur.isEmpty();
    this.valeur = valeur;
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
