package com.github.app.domain;

import com.github.app.domain.constantes.Messages;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;
import core.lib.AggregateRoot;
import core.lib.Argent;

import java.time.ZonedDateTime;

public final class Produit extends AggregateRoot<ProduitId> {

  private Sku sku;
  private String nom;
  private Famille famille;
  private Marque marque;
  private Argent prixUnitaire;
  private Statut statut;
  private AttributsSpecifique  attributsSpecifique;
  private final ZonedDateTime dateDeCreation;
  private ZonedDateTime dateDeDerniereMiseAJour;

  private Produit(Builder builder) {
    super(builder.id);

    setSku(builder.sku);
    setNom(builder.nom);
    setFamille(builder.famille);
    setMarque(builder.marque);
    setPrixUnitaire(builder.prixUnitaire);
    setStatut(builder.statut);
    setAttributsSpecifique(builder.attributsSpecifique);

    dateDeCreation = ZonedDateTime.now();
  }

  public Sku sku() {
    return sku;
  }

  public String nom() {
    return nom;
  }

  public Famille famille() {
    return famille;
  }

  public Marque marque() {
    return marque;
  }

  public Argent prixUnitaire() {
    return prixUnitaire;
  }

  public Statut statut() {
    return statut;
  }

  public AttributsSpecifique  attributsSpecifique() {
    return attributsSpecifique;
  }

  protected void setSku(Sku sku) {
    this.verifieArgumentNonNull(sku, Messages.LE_SKU_DU_PRODUIT_EST_REQUIS);
    this.sku = sku;
  }

  protected void setNom(String nom) {
    this.verifieArgumentNonVide(nom, Messages.LE_NOM_DU_PRODUIT_EST_REQUIS);
    this.nom = nom;
  }

  protected void setFamille(Famille famille) {
    this.verifieArgumentNonNull(famille, Messages.LA_FAMILLE_DU_PRODUIT_EST_REQUISE);
    this.famille = famille;
  }

  protected void setMarque(Marque marque) {
    this.verifieArgumentNonNull(marque, Messages.LA_MARQUE_DU_PRODUIT_EST_REQUISE);
    this.marque = marque;
  }

  protected void setPrixUnitaire(Argent prixUnitaire) {
    this.verifieArgumentNonNull(prixUnitaire, Messages.LE_PRIX_DU_PRODUIT_EST_REQUIS);
    this.prixUnitaire = prixUnitaire;
  }

  protected void setStatut(Statut statut) {
    this.verifieArgumentNonNull(statut, Messages.LE_STATUT_DU_PRODUIT_EST_REQUIS);
    this.statut = statut;
  }

  protected void setAttributsSpecifique(AttributsSpecifique  attributsSpecifique) {
    this.attributsSpecifique = attributsSpecifique;
  }

  public static final class Builder {
    private ProduitId id;
    private Sku sku;
    private String nom;
    private Famille famille;
    private Marque marque;
    private Argent prixUnitaire;
    private Statut statut;
    private AttributsSpecifique attributsSpecifique;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder avecId(ProduitId unIdDeProduit) {
      id = unIdDeProduit;
      return this;
    }

    public Builder avecSku(Sku unStockKeepingUnit) {
      sku = unStockKeepingUnit;
      return this;
    }

    public Builder avecNom(String unNom) {
      nom = unNom;
      return this;
    }

    public Builder avecFamille(Famille uneFamille) {
      famille = uneFamille;
      return this;
    }

    public Builder avecMarque(Marque uneMarque) {
      marque = uneMarque;
      return this;
    }

    public Builder avecPrixUnitaire(Argent unPrix) {
      prixUnitaire = unPrix;
      return this;
    }

    public Builder avecStatut(Statut unStatut) {
      statut = unStatut;
      return this;
    }

    public Builder avecAttributsSpecifique(AttributsSpecifique attributs) {
      attributsSpecifique = attributs;
      return this;
    }

    public Produit build() {
      return new Produit(this);
    }
  }
}
