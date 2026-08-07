package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import core.lib.Adapter;

import com.github.app.domain.PourGererLesProduits;

import java.util.Optional;
import java.util.UUID;

@Adapter(name = "postgres-adapter")
public class GestionnaireDeProduit implements PourGererLesProduits {

  private MapperDePersistenceProduit mapperDePersistenceProduit;
  private RepositoryJpaProduit depotJpa;

  public GestionnaireDeProduit() {}

  public GestionnaireDeProduit(MapperDePersistenceProduit mapperDePersistenceProduit,
                               RepositoryJpaProduit depotJpa) {
    this.mapperDePersistenceProduit = mapperDePersistenceProduit;
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
  public void creerUnProduit(Produit produit) {
    depotJpa.save(mapperDePersistenceProduit.versEntiteJpa(produit));
  }

  @Override
  public Optional<Produit> rechercherUnProduitParId(ProduitId produitId) {
   // Optional<EntiteJpaProduit> resultatRechercheParId = depotJpa.findById(produitId.value());
    //if (resultatRechercheParId.isEmpty())
      return Optional.empty();
    //return Optional.ofNullable();
  }

  @Override
  public void enregistrer(Produit produit) {
    // TODO A SUPPRIMER
  }
}
