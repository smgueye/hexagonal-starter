package com.github.app.application.usecases;

import com.github.app.application.Messages;
import com.github.app.application.commandes.ConsulterUnProduitCommand;
import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionFamilleNonTrouve;
import com.github.app.application.exceptions.ExceptionProduitNonTrouve;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.resultats.ProduitDetail;
import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.Attribut;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;
import com.github.app.domain.valueobject.attributsspecifiques.Texte;
import com.github.app.domain.valueobject.attributsspecifiques.ValeurAttribut;
import core.lib.Argent;
import core.lib.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UseCase(code = "UC1")
public final class GestionnaireDeCatalogue implements PourGererLeCatalogue {

  private static final Logger LOG = LoggerFactory.getLogger(GestionnaireDeCatalogue.class);

  private final PourGererLesProduits pourGererLesProduits;

  public GestionnaireDeCatalogue(PourGererLesProduits pourGererLesProduits) {
    this.pourGererLesProduits = pourGererLesProduits;
  }

  @Override
  // @Transactional
  public ProduitCree creerUnProduit(CreationDeProduitCommand commande) throws ExceptionSkuDejaPresent {
    commande.validate();

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

    pourGererLesProduits.creerUnProduit(produit);

    return new ProduitCree(produit.id(), produit.sku(), produit.statut());
  }

  @Override
  public ProduitDetail consulterUnProduit(ConsulterUnProduitCommand command) {
    command.validate();

    // O5.obersavability : Ajout de champs strucutré
    LOG.atInfo()
        .addKeyValue("traceId", MDC.get("traceId"))
        .addKeyValue("spanId", MDC.get("spanId"))
        .addKeyValue("produit.id", command.id()).log("Produit recherché");

    return pourGererLesProduits
      .rechercherUnProduitParId(new ProduitId(command.id()))
      .map(produit -> new ProduitDetail(
        produit.id().value(),
        produit.sku().valeur(),
        produit.nom(),
        produit.famille().name().toLowerCase(),
        produit.marque().valeur(),
        produit.prixUnitaire().montant(),
        produit.statut().type(),
        produit.attributsSpecifique().attrs()))
      .orElseThrow(() -> new ExceptionProduitNonTrouve(command.id()));
  }

  private AttributsSpecifique enSpecificationsProduit(Map<String, String> specs) {
    return new AttributsSpecifique(specs);
  }
}
