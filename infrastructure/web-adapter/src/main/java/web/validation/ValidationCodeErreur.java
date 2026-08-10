package web.validation;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ValidationCodeErreur {
  REQUIRED("Requis(e)"),
  MUST_BE_POSITIVE("Doit être positif"),
  INVALID_ENUM("Valeur invalide"),
  INVALID("Invalide");

  public static final Map<String, ValidationCodeErreur> PAR_TYPE =
    Arrays
      .stream(ValidationCodeErreur.values())
      .collect(Collectors.toUnmodifiableMap(
        ValidationCodeErreur::name,
        Function.identity()
      ));

  private final String type;

  ValidationCodeErreur(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

  public static ValidationCodeErreur chercherParNom(String unNom) {
    return Optional
      .ofNullable(PAR_TYPE.get(unNom))
      .orElse(INVALID);
  }

  public static ValidationCodeErreur chercherParCodeContrainte(String unCode) {
    return switch (unCode) {
      case "NotBlank", "NotNull" -> REQUIRED;
      case "Positive" -> MUST_BE_POSITIVE;
      case "ValeurEnum" -> INVALID_ENUM;
      default -> INVALID;
    };
  }
}
