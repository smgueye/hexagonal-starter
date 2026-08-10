package com.github.app.domain.agregats;

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
import core.lib.Argent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Agregat Racine - Produit")
public class TestAgregatProduit {

  @Test
  @DisplayName("Lorsque les informations sont valides")
  public void creer_une_instance_de_produit() {
    Produit produit = unProduitBuilder().build();

    // Assert
    assertThat(produit)
      .satisfies(
      p -> assertThat(p.id()).isEqualTo(new ProduitId(UUID.fromString("54b485e1-f3f7-45ed-b971-4ce18c32da7a"))),
      p -> assertThat(produit.sku()).isEqualTo(new Sku("TRN-PRC-500W")),
      p -> assertThat(produit.nom()).isEqualTo("Perceuse sans fil 500W"),
      p -> assertThat(produit.famille()).isEqualTo(Famille.OUTILLAGE),
      p ->  assertThat(produit.marque()).isEqualTo(new Marque("Bosch")),
      p -> assertThat(produit.prixUnitaire()).isEqualTo(new Argent(new BigDecimal("89.90"))),
      p ->  assertThat(produit.statut()).isEqualTo(Statut.ACTIF));

    assertAttribut(produit.attributsSpecifique(), "puissance", "500w");
    assertAttribut(produit.attributsSpecifique(), "poids","1.8");
    assertAttribut(produit.attributsSpecifique(), "unite", "KG");
  }

  private static void assertAttribut(AttributsSpecifique attributs, String nom, String valeurAttendue) {
    assertThat(attributs.obtenir(nom))
      .as("attribut spécifique <%s>", nom)
      .hasValueSatisfying(attribut -> {
        assertThat(attribut).isNotNull();
        assertThat(attribut).isEqualTo(valeurAttendue);
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
        unProduitBuilder()
          .avecId(null)
          .build();
      })
          .as("La création d'un produit sans identifiant est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.L_IDENTIFIANT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque le sku n'est pas renseigné.")
    void refuse_un_sku_absent() {
      assertThatThrownBy(() -> {
        unProduitBuilder().avecSku(null).build();
      })
          .as("La création d'un produit sans sku est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_SKU_DU_PRODUIT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque le nom n'est pas renseigné.")
    void refuse_un_nom_vide() {
      assertThatThrownBy(() -> {
        unProduitBuilder()
          .avecNom("")
          .build();
      })
          .as("La création d'un produit sans nom est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_NOM_DU_PRODUIT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque la famille n'est pas renseignée.")
    void refuse_une_famille_absente() {
      assertThatThrownBy(() -> {
        unProduitBuilder()
          .avecFamille(null)
          .build();
      })
          .as("La création d'un produit sans identifiant est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LA_FAMILLE_DU_PRODUIT_EST_REQUISE);
    }

    @Test
    @DisplayName("Lorsque la marque n'est pas renseignée.")
    void refuse_une_marque_absente() {
      assertThatThrownBy(() -> {
        unProduitBuilder()
          .avecMarque(null)
          .build();
      })
          .as("La création d'un produit sans marque est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LA_MARQUE_DU_PRODUIT_EST_REQUISE);
    }

    @Test
    @DisplayName("Lorsque le prix n'est pas renseigné.")
    void refuse_un_prix_absent() {
      assertThatThrownBy(() -> {
        unProduitBuilder()
          .avecPrixUnitaire(null)
          .build();
      })
          .as("La création d'un produit sans prix est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_PRIX_DU_PRODUIT_EST_REQUIS);
    }

    @Test
    @DisplayName("Lorsque le statut n'est pas renseigné.")
    void refuse_un_statut_absent() {
      assertThatThrownBy(() -> {
        unProduitBuilder()
          .avecStatut(null)
          .build();
      })
          .as("La création d'un produit sans statut est refusée.")
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage(Messages.LE_STATUT_DU_PRODUIT_EST_REQUIS);
    }
  }

  private Produit.Builder unProduitBuilder() {
    return Produit.Builder
      .builder()
        .avecId(new ProduitId(UUID.fromString("54b485e1-f3f7-45ed-b971-4ce18c32da7a")))
        .avecSku(new Sku("TRN-PRC-500W"))
        .avecNom("Perceuse sans fil 500W")
        .avecFamille(Famille.OUTILLAGE)
        .avecMarque(new Marque("Bosch"))
        .avecPrixUnitaire(new Argent(new BigDecimal("89.90")))
        .avecStatut(Statut.ACTIF)
        .avecAttributsSpecifique(new AttributsSpecifique(Map.of(
          "puissance", "500w",
          "poids","1.8",
          "unite", "KG")));
  }
}
