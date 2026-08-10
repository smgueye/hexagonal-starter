package com.github.app.application.casutilisations;

import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionFamilleNonTrouve;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.fakes.ProduitEnMemoire;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.usecases.GestionnaireDeCatalogue;
import com.github.app.domain.Produit;
import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.Marque;
import com.github.app.domain.valueobject.Statut;
import com.github.app.domaintestsupport.fixtures.ProduitFixtureBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("UC1 - Création de produit")
public class TestGestionnaireDeCatalogue {

  private final ProduitEnMemoire persistence = new ProduitEnMemoire();
  private final GestionnaireDeCatalogue gestionnaireDeCatalogue = new GestionnaireDeCatalogue(persistence);

  @Test
  @DisplayName("Happy path")
  public void the_happy_path() throws ExceptionSkuDejaPresent {
    // Given
    CreationDeProduitCommand commande = obtenirUneCommandeValide(null);

    // When
    ProduitCree produitCree = gestionnaireDeCatalogue.creerUnProduit(commande);

    // Then
    assertThat(produitCree)
      .satisfies(
        p -> assertThat(p.id()).isNotNull(),
        p -> assertThat(p.sku().valeur()).isEqualTo(commande.sku()),
        p -> assertThat(p.statut()).isEqualTo(Statut.ACTIF));
  }

  protected CreationDeProduitCommand obtenirUneCommandeValide(String uneFamille) {
    if (uneFamille == null) {
      uneFamille = Famille.OUTILLAGE.type();
    }
    return new CreationDeProduitCommand(
      "TRN-PRC-500W",
      "Perceuse sans fil 500W",
      uneFamille,
      "Bosch",
      new BigDecimal("89.90"),
      Map.of(
        "puissance", "500W",
        "poids", "1.8",
        "unite", "KG")
    );
  }

  @Nested
  @DisplayName("Lors de la création")
  class LorsDeLaCreation {

    @Test
    @DisplayName("Lorsque le nom de la famille n'existe pas.")
    void refuse_un_nom_de_famille_non_repertorie() {
      // Act
      // Given
      assertThatThrownBy(() -> {
        gestionnaireDeCatalogue.creerUnProduit(obtenirUneCommandeValide("XXX"));
      })
          .as("La création d'un produit avec une famille inexistante est refusée.")
          .isInstanceOf(ExceptionFamilleNonTrouve.class);
    }

    @Test
    @DisplayName("Lorsque le sku est déjà existant en base.")
    void refuse_un_sku_deja_existant() {
      // Act
      Produit produit = ProduitFixtureBuilder
        .unBuilderAvecDonneesValides()
        .avecFamille(Famille.BRICOLAGE)
        .avecMarque(new Marque("YYY"))
        .avecNom("XXXX")
        .build();
      persistence.creerUnProduit(produit);

      // Given
      assertThatThrownBy(() -> {
        gestionnaireDeCatalogue.creerUnProduit(obtenirUneCommandeValide(null));
      })
        .as("La création d'un produit avec un sku déjà utilisé est refusée.")
        .isInstanceOf(ExceptionSkuDejaPresent.class);
    }
  }
}
