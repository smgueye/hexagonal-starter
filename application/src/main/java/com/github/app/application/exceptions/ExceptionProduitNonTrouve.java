package com.github.app.application.exceptions;

import java.util.UUID;

public class ExceptionProduitNonTrouve extends RuntimeException {

  private  final UUID produitId;

  public ExceptionProduitNonTrouve(UUID produitId) {
    super("Le produit avec un id " + produitId + " introuvable !");
    this.produitId = produitId;
  }

  public UUID getProduitId() {
    return produitId;
  }
}
