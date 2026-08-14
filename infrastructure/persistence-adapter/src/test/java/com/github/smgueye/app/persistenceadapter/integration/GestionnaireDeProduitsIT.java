package com.github.smgueye.app.persistenceadapter.integration;

import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domain.valueobject.Sku;
import com.github.app.domain.valueobject.attributsspecifiques.*;
import com.github.app.domaintestsupport.fixtures.ProduitFixtureBuilder;
import com.github.smgueye.app.persistenceadapter.AttributJson;
import com.github.smgueye.app.persistenceadapter.EntiteJpaProduit;
import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import com.github.smgueye.app.persistenceadapter.RepositoryJpaProduit;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Testcontainers
@SpringBootTest(
    classes = PersistenceAdapterConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = "spring.config.name=persistence-adapter"
)
@Disabled("Entire class disabled for due to architecture review.")
public class GestionnaireDeProduitsIT {

  @Container
  @ServiceConnection
  static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:17.6");
  // TODO postgres:17.6 a mettre en EVM

  @Autowired
  private PourGererLesProduits gestionnaireDeProduits;

  @Test
  @DisplayName("Persister un produit en base de données")
  public void persister_un_produit_en_base_de_donnees() {
    Produit produit = ProduitFixtureBuilder
      .unBuilderAvecDonneesValides()
      .avecSku(new Sku(String.format("TRN-%s", UUID.randomUUID())))
      .build();

    gestionnaireDeProduits.creerUnProduit(produit);

    assertThat(gestionnaireDeProduits.existeAvecUnSku(produit.sku())).isTrue();
  }


  @Nested
  @DisplayName("Lors de la persistence d'un produit")
  class LorsDeLaPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    RepositoryJpaProduit repositoryJpaProduit;

    @Test
    public void persister_un_produit_en_base_de_donnees() {
      Sku sku = new Sku(String.format("TRN-%s", UUID.randomUUID()));
      Produit premier = ProduitFixtureBuilder
        .unBuilderAvecDonneesValides()
        .avecSku(sku)
        .build();
      Produit second = ProduitFixtureBuilder
        .unBuilderAvecDonneesValides()
        .avecSku(sku)
        .build();

      gestionnaireDeProduits.creerUnProduit(premier);

      assertThatThrownBy(() -> {
        gestionnaireDeProduits.creerUnProduit(second);
        entityManager.flush();
      }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void verifier_les_attributs_specifiques_jsonb() {
      String sku = "TEST-" + UUID.randomUUID();

      Produit produit = ProduitFixtureBuilder.unBuilderAvecDonneesValides()
        .avecSku(new Sku(sku))
        .avecAttributsSpecifique(new AttributsSpecifique(Map.of(
          "couleur", "Rouge",
          "puissance", "500",
          "disponible", "true"))
        ).build();

      gestionnaireDeProduits.creerUnProduit(produit);

      EntiteJpaProduit produitRelu = repositoryJpaProduit
        .findBySku(sku)
        .orElseThrow();

      assertThat(produitRelu.attributsSpecifiques())
        .containsEntry("couleur", "Rouge")
        .containsEntry("puissance", "500")
        .containsEntry("disponible", "true");
    }
  }
}
