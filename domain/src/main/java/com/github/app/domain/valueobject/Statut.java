package com.github.app.domain.valueobject;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Statut {
  ACTIF("Actif"),
  ARCHIVE("Archivé");

  public static final Map<String, Statut> PAR_NOM = Arrays
      .stream(Statut.values())
      .collect(Collectors.toUnmodifiableMap(Statut::name, Function.identity()));

  private final String type;

  Statut(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

  public static Optional<Statut> chercherParType(String unType) {
    return Optional.ofNullable(PAR_NOM.get(unType.toUpperCase()));
  }
}
