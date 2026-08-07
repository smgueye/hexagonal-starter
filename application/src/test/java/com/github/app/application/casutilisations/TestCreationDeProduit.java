package com.github.app.application.casutilisations;

import com.github.app.application.Messages;
import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionMetier;
import com.github.app.application.fakes.ProduitEnMemoire;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.usecases.CreationDeProduit;
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
public class TestCreationDeProduit {

  private final ProduitEnMemoire persistence = new ProduitEnMemoire();
  private final CreationDeProduit creationDeProduit = new CreationDeProduit(persistence);

  @Test
  @DisplayName("Happy path")
  public void the_happy_path() {
    // Given
    CreationDeProduitCommand commande = obtenirUneCommandeValide(null);

    // When
    ProduitCree produitCree = creationDeProduit.creerUnProduit(commande);

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
        "puissance", Map.of("puissance", "500W"),
        "poids", Map.of("poids", "1.8"),
        "unite", Map.of("unite", "KG"))
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
        creationDeProduit.creerUnProduit(obtenirUneCommandeValide("XXX"));
      })
          .as("La création d'un produit avec une famille inexistante est refusée.")
          .isInstanceOf(ExceptionMetier.class)
          .hasMessage(Messages.LA_FAMILLE_PAS_RECONNUE);
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
      persistence.enregistrer(produit);

      // Given
      assertThatThrownBy(() -> {
        creationDeProduit.creerUnProduit(obtenirUneCommandeValide(null));
      })
        .as("La création d'un produit avec un sku déjà utilisé est refusée.")
        .isInstanceOf(ExceptionMetier.class)
        .hasMessage(Messages.LE_SKU_DEJA_UTILISE);
    }
  }
}
