package com.github.app.application.resultats;

import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;

public record ProduitCree(ProduitId id,
                          Sku sku,
                          Statut statut) {

  @Override
  public String toString() {
    return "ProduitCree={id=" + id + ", sku=" + sku + ", statut=" + statut + "}";
  }
}
