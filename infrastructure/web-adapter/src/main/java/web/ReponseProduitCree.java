package web;

import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;

import java.util.UUID;


public record ReponseProduitCree(UUID id,
                                 String sku,
                                 String statut) {

  @Override
  public String toString() {
    return "ReponseProduitCree={id=" + id + ", sku=" + sku + ", statut=" + statut + "}";
  }
}

