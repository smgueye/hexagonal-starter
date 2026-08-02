package core.lib;

import java.util.Objects;

public class ValidationAssertive {
  public void verifieArgumentEstNull(Object unObjet, String unMessage) {
    if (Objects.nonNull(unObjet)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

  public void verifieArgumentNonNull(Object unObjet, String unMessage) {
    if (Objects.isNull(unObjet)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

  public void verifieArgumentNonVide(String uneChaineDeCaracteres, String unMessage) {
    this.verifieArgumentNonNull(uneChaineDeCaracteres, unMessage);

    if (uneChaineDeCaracteres.trim().isEmpty()) {
      throw new IllegalArgumentException(unMessage);
    }
  }
}
