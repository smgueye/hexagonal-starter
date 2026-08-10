package web.errors;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CodeErreur {
  SKU_DEJA_PRESENT("Sku déjà présent"),
  PRODUIT_NON_TROUVE("Produit non trouvé");

  public static final Map<String, CodeErreur> PAR_TYPE =
    Arrays.stream(CodeErreur.values())
      .collect(Collectors.toUnmodifiableMap(
        CodeErreur::type,
        Function.identity()));

  private final String type;

  CodeErreur(String type) {
    this.type = type;
  }

  public String type() {
    return type;
  }

  public static Optional<CodeErreur> chercherParCode(String unCode) {
    return Optional.ofNullable(PAR_TYPE.get(unCode));
  }
}
