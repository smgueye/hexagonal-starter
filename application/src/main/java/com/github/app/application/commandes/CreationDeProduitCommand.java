package com.github.app.application.commandes;

import core.lib.ValidateCommand;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public record CreationDeProduitCommand(
    String sku,
    String nom,
    String famille,
    String marque,
    BigDecimal prixUnitaire,
    Map<String, String> attributsSpecifiques) implements ValidateCommand {

  @Override
  public void validate() {
    Objects.requireNonNull(sku, "Le sku est requis.");
    Objects.requireNonNull(nom, "Le nom est requis.");
    Objects.requireNonNull(famille, "Le famille est requise.");
    Objects.requireNonNull(marque, "Le marque est requise.");
    Objects.requireNonNull(prixUnitaire, "Le prixUnitaire est requis.");
    assert prixUnitaire.compareTo(BigDecimal.ZERO) > 0;
  }
}
