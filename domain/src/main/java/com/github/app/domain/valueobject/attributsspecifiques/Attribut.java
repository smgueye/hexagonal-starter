package com.github.app.domain.valueobject.attributsspecifiques;

public record Attribut(String nom, ValeurAttribut valeur) {
  public Attribut {
    if (nom == null || nom.isBlank()) throw new IllegalArgumentException("nom d'attribut requis");
  }
}
