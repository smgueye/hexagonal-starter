package com.github.app.domain.valueobject;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Statut {
  ACTIF("Actif"),
  ARCHIVE("Archivé");

  public static final Map<String, Statut> PAR_TYPE = Arrays
      .stream(Statut.values())
      .collect(Collectors.toUnmodifiableMap(Statut::type, Function.identity()));

  private final String type;

  Statut(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

  public static Optional<Statut> chercherParType(String unType) {
    return Optional.ofNullable(PAR_TYPE.get(unType));
  }
}
