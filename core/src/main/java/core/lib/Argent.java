package core.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Argent extends ValueObject {

  private final BigDecimal montant;

  public Argent(BigDecimal montant) {
    Objects.requireNonNull(montant);
    this.montant = montant;
  }

  public BigDecimal montant() {
    return setScale(montant);
  }

  public Boolean estSuperieurAZero() {
    return montant.compareTo(BigDecimal.ZERO) > 0;
  }

  public Boolean estSuperieurA(Argent argent) {
    return montant.compareTo(argent.montant()) > 0;
  }

  private BigDecimal setScale(BigDecimal valeur) {
    return valeur.setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Argent argent = (Argent) o;
    return Objects.equals(montant, argent.montant);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(montant);
  }
}
