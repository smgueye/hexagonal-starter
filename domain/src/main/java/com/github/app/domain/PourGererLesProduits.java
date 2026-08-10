package com.github.app.domain;

import com.github.app.domain.valueobject.Sku;

import java.util.Optional;

// Port ou Design doc
public interface PourGererLesProduits {

  ProduitId uneIdentite();

  boolean existeAvecUnSku(Sku sku);

  Optional<Produit> rechercherUnProduitParId(ProduitId produitId);

  void creerUnProduit(Produit produit);
}
