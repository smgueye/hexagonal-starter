package com.github.app.domain.valueobject;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Famille {
  OUTILLAGE("Outillage"),
  BRICOLAGE("Bricolage"),
  JARDIN("Jardin"),
  DECORATION("Decoration"),
  ELECTROMENAGER("Electroménager"),
  QUINCAILLERIE("Quincaillerie");

  public static final Map<String, Famille> PAR_TYPE =
    Arrays
      .stream(Famille.values())
      .collect(Collectors.toUnmodifiableMap(
        Famille::type,
        Function.identity()
      ));

  private final String type;

  Famille(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

  public static Optional<Famille> chercherParType(String unType) {
    return Optional.ofNullable(PAR_TYPE.get(unType));
  }
}