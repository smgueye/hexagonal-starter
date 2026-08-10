package com.github.app.domain.valueobject.attributsspecifiques;

import core.lib.ValueObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class AttributsSpecifique extends ValueObject {

  private final Map<String, String> attributs;

  public AttributsSpecifique(Map<String, String> attributs) {
    this.attributs = Map.copyOf(attributs);
  }

  public Optional<String> obtenir(String nom) {
    return Optional.ofNullable(attributs.get(nom));
  }

  // TODO - Equals & Hash

  public Map<String, String> attrs() {
    return Map.copyOf(attributs);
  }
}
