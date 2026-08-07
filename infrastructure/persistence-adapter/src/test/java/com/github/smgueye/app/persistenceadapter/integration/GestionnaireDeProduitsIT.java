package com.github.smgueye.app.persistenceadapter.integration;

import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domaintestsupport.fixtures.ProduitFixtureBuilder;
import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(
    classes = PersistenceAdapterConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = "spring.config.name=persistence-adapter"
)
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
    Produit produit = ProduitFixtureBuilder.unBuilderAvecDonneesValides().build();

    gestionnaireDeProduits.enregistrer(produit);

    assertThat(gestionnaireDeProduits.existeAvecUnSku(produit.sku())).isTrue();
  }
}
