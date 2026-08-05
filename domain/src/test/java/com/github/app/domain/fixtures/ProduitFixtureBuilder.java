package com.github.app.domain.fixtures;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.Attribut;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;
import com.github.app.domain.valueobject.attributsspecifiques.Numerique;
import com.github.app.domain.valueobject.attributsspecifiques.Texte;
import core.lib.Argent;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public final class ProduitFixtureBuilder {

  private Produit.Builder produitBuilder = Produit.Builder.builder();

  private ProduitFixtureBuilder(Produit.Builder produitBuilder) {
    this.produitBuilder = produitBuilder;
  }

  public static ProduitFixtureBuilder unBuilderAvecDonneesValides() {
    return new ProduitFixtureBuilder(Produit.Builder
      .builder()
        .avecId(new ProduitId(UUID.randomUUID()))
        .avecSku(new Sku("TRN-PRC-500W"))
        .avecNom("Perceuse sans fil 500W")
        .avecFamille(Famille.OUTILLAGE)
        .avecMarque(new Marque("Bosch"))
        .avecPrixUnitaire(new Argent(new BigDecimal("89.90")))
        .avecStatut(Statut.ACTIF)
        .avecAttributsSpecifique(new AttributsSpecifique(Map.of(
            "puissance", new Attribut("puissance", new Texte("500w")),
            "poids", new Attribut("poids", new Numerique(1.8)),
            "unite", new Attribut("unite", new Texte("KG"))
        ))));
  }

  public ProduitFixtureBuilder avecId(ProduitId unIdDeProduit) {
    produitBuilder.avecId(unIdDeProduit);
    return this;
  }

  public ProduitFixtureBuilder avecSku(Sku unStockKeepingUnit) {
    produitBuilder.avecSku(unStockKeepingUnit);
    return this;
  }

  public ProduitFixtureBuilder avecNom(String unNom) {
    produitBuilder.avecNom(unNom);
    return this;
  }

  public ProduitFixtureBuilder avecFamille(Famille uneFamille) {
    produitBuilder.avecFamille(uneFamille);
    return this;
  }

  public ProduitFixtureBuilder avecMarque(Marque uneMarque) {
    produitBuilder.avecMarque(uneMarque);
    return this;
  }

  public ProduitFixtureBuilder avecPrixUnitaire(Argent unPrix) {
    produitBuilder.avecPrixUnitaire(unPrix);
    return this;
  }

  public ProduitFixtureBuilder avecStatut(Statut unStatut) {
    produitBuilder.avecStatut(unStatut);
    return this;
  }

  public ProduitFixtureBuilder avecAttributsSpecifique(AttributsSpecifique attributs) {
    produitBuilder.avecAttributsSpecifique(attributs);
    return this;
  }

  public Produit build() {
    return produitBuilder.build();
  }
}
