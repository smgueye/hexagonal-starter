package com.github.app.domain.valueobject.attributsspecifiques;

import core.lib.ValueObject;

import java.util.Map;
import java.util.Optional;

public final class AttributsSpecifique extends ValueObject {

  private final Map<String, Attribut> attributs;

  public AttributsSpecifique(Map<String, Attribut> attributs) {
    this.attributs = Map.copyOf(attributs);
  }

  public Optional<Attribut> obtenir(String nom) {
    return Optional.ofNullable(attributs.get(nom));
  }

  // TODO - Equals & Hash
}
