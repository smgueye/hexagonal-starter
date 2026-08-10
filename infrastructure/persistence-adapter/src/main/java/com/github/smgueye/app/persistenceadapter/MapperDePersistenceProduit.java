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

  private Map<String, String> versJson(AttributsSpecifique attributs) {
    if (attributs == null)
      return Collections.emptyMap();

    return attributs.attrs();
  }

  public Produit versProduit(EntiteJpaProduit entite) {
    return Produit.Builder.builder()
        .avecId(new ProduitId(entite.id()))
        .avecSku(new Sku(entite.sku()))
        .avecNom(entite.nom())
        .avecFamille(Famille.chercherParNom(entite.famille()).orElseThrow())
        .avecMarque(new Marque(entite.marque()))
        .avecPrixUnitaire(new Argent(entite.prixUnitaire()))
        .avecStatut(Statut.chercherParType(entite.statut()).orElseThrow())
        .avecAttributsSpecifique(new AttributsSpecifique(entite.attributsSpecifiques()))
        .build();
  }
}
