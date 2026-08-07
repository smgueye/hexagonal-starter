package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import core.lib.Argent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMapperDePersistenceProduit {

  private final MapperDePersistenceProduit mapper = new MapperDePersistenceProduit();

  @Test
  @DisplayName("Conversion d'un produit en une entite produit")
  public void conversion_d_un_produit_en_une_entite_produit() {
    Produit produit = Produit.Builder
      .builder()
        .avecId(new ProduitId(UUID.randomUUID()))
        .avecSku(new Sku("TRN-PRC-500W"))
        .avecNom("Perceuse")
        .avecFamille(Famille.OUTILLAGE)
        .avecMarque(new Marque("Bosch"))
        .avecPrixUnitaire(new Argent(new BigDecimal("89.90")))
        .avecStatut(Statut.ACTIF)
      .build();

    EntiteJpaProduit entity = mapper.versEntiteJpa(produit);

    assertThat(entity)
      .satisfies(
        e -> assertThat(e.id()).isEqualTo(produit.id().value()),
        e -> assertThat(e.sku()).isEqualTo(produit.sku().valeur()),
        e -> assertThat(e.nom()).isEqualTo(produit.nom()),
        e -> assertThat(e.famille()).isEqualTo(produit.famille().name()),
        e -> assertThat(e.marque()).isEqualTo(produit.marque().valeur()),
        e -> assertThat(e.prixUnitaire()).isEqualTo(produit.prixUnitaire().montant()),
        e -> assertThat(e.statut()).isEqualTo(produit.statut().name()),
        e -> assertThat(e.id()).isEqualTo(produit.id().value()));
  }
}
