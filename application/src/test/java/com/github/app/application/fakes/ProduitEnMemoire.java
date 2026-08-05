package com.github.app.application.fakes;

import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProduitEnMemoire implements PourGererLesProduits {
  private final Map<ProduitId, Produit> produits = new HashMap<>();

  @Override
  public ProduitId uneIdentite() {
    return new ProduitId(UUID.randomUUID());
  }

  @Override
  public boolean existeAvecUnSku(Sku sku) {
    return produits
        .values()
        .stream()
        .anyMatch(produit ->  produit.sku().equals(sku));
  }

  @Override
  public void creerUnProduit(Produit produit) {
    produits.put(produit.id(), produit);
  }

  @Override
  public Optional<Produit> rechercherUnProduitParId(ProduitId produitId) {
    return Optional.ofNullable(produits.get(produitId));
  }

  @Override
  public void enregistrer(Produit produit) {
    produits.put(produit.id(), produit);
  }
}
