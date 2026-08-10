package com.github.app.application.commandes;

import core.lib.ValidateCommand;

import java.util.Objects;
import java.util.UUID;

public record ConsulterUnProduitCommand(UUID id) implements ValidateCommand {

  @Override
  public void validate() {
    Objects.requireNonNull(id, "L'identifiant du produit est requis.");
  }
}
