package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import core.lib.Adapter;

import com.github.app.domain.PourGererLesProduits;

import java.util.Optional;
import java.util.UUID;

public class GestionnaireDeProduit implements PourGererLesProduits {

  private MapperDePersistenceProduit mapperDePersistenceProduit;
  private RepositoryJpaProduit depotJpa;

  public GestionnaireDeProduit() {}

  public GestionnaireDeProduit(MapperDePersistenceProduit mapperDePersistence,
                               RepositoryJpaProduit depotJpa) {
    this.mapperDePersistenceProduit = mapperDePersistence;
    this.depotJpa = depotJpa;
  }

  @Override
  public ProduitId uneIdentite() {
    return new ProduitId(UUID.randomUUID());
  }

  @Override
  public boolean existeAvecUnSku(Sku sku) {
    return depotJpa.existsBySku(sku.valeur());
  }

  @Override
  public void enregistrer(Produit produit) {
    depotJpa.save(mapperDePersistenceProduit.versEntiteJpa(produit));
  }

  @Override
  public Optional<Produit> rechercherUnProduitParId(ProduitId produitId) {
    return depotJpa.findById(produitId.value()).map(mapperDePersistenceProduit::versProduit);
  }

  @Override
  public void creerUnProduit(Produit produit) {
    // TODO A SUPPRIMER
  }
}
