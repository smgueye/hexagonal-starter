package com.github.app.application.usecases;

import com.github.app.application.Messages;
import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionFamilleNonTrouve;
import com.github.app.application.exceptions.ExceptionMetier;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.Attribut;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;
import com.github.app.domain.valueobject.attributsspecifiques.Texte;
import core.lib.Argent;
import core.lib.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UseCase(code = "UC1")
public final class CreationDeProduit implements PourGererLeCatalogue {

  private static final Logger LOG = LoggerFactory.getLogger(CreationDeProduit.class);

  private final PourGererLesProduits pourGererLesProduits;

  public CreationDeProduit(PourGererLesProduits pourGererLesProduits) {
    this.pourGererLesProduits = pourGererLesProduits;
  }

  @Override
  public ProduitCree creerUnProduit(CreationDeProduitCommand commande) throws ExceptionSkuDejaPresent {
    Optional<Famille> resultat = Famille.chercherParNom(commande.famille());
    if (resultat.isEmpty()) {
      throw new ExceptionFamilleNonTrouve(commande.famille());
    }

    Sku sku = new Sku(commande.sku());
    if (pourGererLesProduits.existeAvecUnSku(sku))
      throw new ExceptionSkuDejaPresent(Messages.LE_SKU_DEJA_UTILISE);

    Produit produit = Produit.Builder
      .builder()
        .avecId(pourGererLesProduits.uneIdentite())
        .avecSku(sku)
        .avecNom(commande.nom())
        .avecFamille(resultat.get())
        .avecMarque(new Marque(commande.marque()))
        .avecPrixUnitaire(new Argent(commande.prixUnitaire()))
        .avecStatut(Statut.ACTIF)
        .avecAttributsSpecifique(enSpecificationsProduit(commande.attributsSpecifiques()))
      .build();

    pourGererLesProduits.enregistrer(produit);

    var produitCree = new ProduitCree(produit.id(), produit.sku(), produit.statut());
    LOG.info("Produit créé application={}", produitCree);
    return produitCree;
  }

  private AttributsSpecifique enSpecificationsProduit(Map<String, Map<String, String>> specs) {
    Map<String, Attribut> attributsSpecifiques = new HashMap<>();
    specs.forEach((key, attr) -> {
      attributsSpecifiques.put(key, new Attribut(key, new Texte(attr.get(key))));
    });
    return new AttributsSpecifique(attributsSpecifiques);
  }
}
