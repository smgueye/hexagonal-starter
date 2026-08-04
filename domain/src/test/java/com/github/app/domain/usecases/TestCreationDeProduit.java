package com.github.app.domain.usecases;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.constantes.Messages;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domain.valueobject.attributsspecifiques.Attribut;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;
import com.github.app.domain.valueobject.attributsspecifiques.Numerique;
import com.github.app.domain.valueobject.attributsspecifiques.Texte;
import common.fixtures.ProduitFixtureBuilder;
import core.lib.Argent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("UC1 - Création de produit")
public class TestCreationDeProduit {

  @Test
  @DisplayName("Lorsque les informations sont valides")
  public void creer_une_instance_de_produit() {
    // Given
    ProduitId id = new ProduitId(UUID.fromString("54b485e1-f3f7-45ed-b971-4ce18c32da7a"));
    Sku unSku = new Sku("TRN-PRC-500W");
    String unNom = "Perceuse sans fil 500W";
    Famille uneFamille = Famille.OUTILLAGE;
    Marque uneMarque = new Marque("Bosch");
    Argent unPrixUnitaire = new Argent(new BigDecimal("89.90"));
    Statut unStatut = Statut.ACTIF;
    AttributsSpecifique attributs = new AttributsSpecifique(Map.of(
    "puissance", new Attribut("puissance", new Texte("500w")),
    "poids", new Attribut("poids", new Numerique(1.8)),
    "unite", new Attribut("unite", new Texte("KG"))));

    // When
    Produit produit = Produit.Builder
      .builder()
      .avecId(id)
      .avecSku(unSku)
      .avecNom(unNom)
      .avecFamille(uneFamille)
      .avecMarque(uneMarque)
      .avecPrixUnitaire(unPrixUnitaire)
      .avecStatut(unStatut)
      .avecAttributsSpecifique(attributs)
      .build();

    // Assert
    assertThat(produit).satisfies(
      (p) -> assertThat(p.id()).isEqualTo(id),
      (p) -> assertThat(produit.sku()).isEqualTo(unSku),
      (p) -> assertThat(produit.nom()).isEqualTo(unNom),
      (p) -> assertThat(produit.famille()).isEqualTo(uneFamille),
      (p) ->  assertThat(produit.marque()).isEqualTo(uneMarque),
      (p) -> assertThat(produit.prixUnitaire()).isEqualTo(unPrixUnitaire),
      (p) ->  assertThat(produit.statut()).isEqualTo(unStatut));

    assertAttribut(
      produit.attributsSpecifique(),
      "puissance",
      new Texte("500w"));
    assertAttribut(
      produit.attributsSpecifique(),
      "poids",
      new Numerique(1.8));
    assertAttribut(
      produit.attributsSpecifique(),
      "unite",
      new Texte("KG"));
  }

  @Test
  void doit_echouer_pour_tester_la_ci() {
    assertEquals(2, 1);
  }

  private static void assertAttribut(AttributsSpecifique attributs, String nom, Object valeurAttendue) {
    assertThat(attributs.obtenir(nom))
      .as("attribut spécifique <%s>", nom)
      .hasValueSatisfying(attribut -> {
        assertThat(attribut.nom()).isEqualTo(nom);
        assertThat(attribut.valeur()).isEqualTo(valeurAttendue);
      });
  }

  /**
   * TODO -- Note - Un aspect pourrait gérer la redondance du code à ce niveau si la documentation ne donne rien.
   */
  @Nested
  @DisplayName("Lors de la création")
  class LorsDeLaCreation {

    @Test
    @DisplayName("Lorsque l'identifiant n'est pas renseigné.")
    void refuse_un_identifiant_absent() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecId(null).build();
      })
          .as("La création d'un produit sans identifiant est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.L_IDENTIFIANT_DU_PRODUIT_EST_REQUIS);;
    }

    @Test
    @DisplayName("Lorsque le sku n'est pas renseigné.")
    void refuse_un_sku_absent() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecSku(null).build();
      })
          .as("La création d'un produit sans sku est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_SKU_DU_PRODUIT_EST_REQUIS);;
    }

    @Test
    @DisplayName("Lorsque le nom n'est pas renseigné.")
    void refuse_un_nom_vide() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecNom("").build();
      })
          .as("La création d'un produit sans nom est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_NOM_DU_PRODUIT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque la famille n'est pas renseignée.")
    void refuse_une_famille_absente() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecFamille(null).build();
      })
          .as("La création d'un produit sans identifiant est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LA_FAMILLE_DU_PRODUIT_EST_REQUISE);
    }

    @Test
    @DisplayName("Lorsque la marque n'est pas renseignée.")
    void refuse_une_marque_absente() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecMarque(null).build();
      })
          .as("La création d'un produit sans marque est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LA_MARQUE_DU_PRODUIT_EST_REQUISE);
    }

    @Test
    @DisplayName("Lorsque le prix n'est pas renseigné.")
    void refuse_un_prix_absent() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder.unBuilderAvecDonneesValides().avecPrixUnitaire(null).build();
      })
          .as("La création d'un produit sans prix est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_PRIX_DU_PRODUIT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque le statut n'est pas renseigné.")
    void refuse_un_statut_absent() {
      assertThatThrownBy(() -> {
        ProduitFixtureBuilder
          .unBuilderAvecDonneesValides()
          .avecStatut(null)
          .build();
      })
          .as("La création d'un produit sans statut est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_STATUT_DU_PRODUIT_EST_REQUIS);
    }
  }
}
