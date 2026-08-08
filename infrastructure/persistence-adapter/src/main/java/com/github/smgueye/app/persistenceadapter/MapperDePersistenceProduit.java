package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.*;
import core.lib.Argent;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MapperDePersistenceProduit {

  public EntiteJpaProduit versEntiteJpa(Produit produit) {
    return EntiteJpaProduit
      .builder()
        .id(produit.id().value())
        .nom(produit.nom())
        .sku(produit.sku().valeur())
        .famille(produit.famille().name())
        .marque(produit.marque().valeur())
        .prixUnitaire(produit.prixUnitaire().montant())
        .statut(produit.statut().name())
        .attributsSpecifiques(Map.copyOf(versJson(produit.attributsSpecifique())))
        .creeLe(produit.dateDeCreation().toInstant())
      .build();
  }

  private Map<String, AttributJson> versJson(AttributsSpecifique attributs) {
    if (attributs == null)
      return Collections.emptyMap();

    return attributs
      .tous()
      .stream()
      .collect(Collectors.toUnmodifiableMap(Attribut::nom, this::versJson));
  }

  private AttributJson versJson(Attribut attribut) {
    return switch (attribut.valeur()) {
      case Texte texte -> AttributJson.texte(texte.valeur());
      case Numerique numerique -> AttributJson.numerique(numerique.valeur());
      case Booleen booleen -> AttributJson.booleen(booleen.valeur());
    };
  }

  public Produit versProduit(EntiteJpaProduit entite) {
    return Produit.Builder.builder()
        .avecId(new ProduitId(entite.id()))
        .avecSku(new Sku(entite.sku()))
        .avecNom(entite.nom())
        .avecFamille(Famille.chercherParType(entite.famille()).orElseThrow())
        .avecMarque(new Marque(entite.marque()))
        .avecPrixUnitaire(new Argent(entite.prixUnitaire()))
        .avecStatut(Statut.chercherParType(entite.statut()).orElseThrow())
        // TODO .avecAttributsSpecifique(entite.attributsSpecifiques())
        // TODO .
        .build();
  }
}
