package com.github.smgueye.app.persistenceadapter.integration;


import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domaintestsupport.fixtures.ProduitFixtureBuilder;
import com.github.smgueye.app.persistenceadapter.GestionnaireDeProduit;
import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import com.github.smgueye.app.persistenceadapter.RepositoryJpaProduit;
import core.lib.Transaction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@SpringBootTest(
    classes = PersistenceAdapterConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = "spring.config.name=persistence-adapter"
)
@Disabled("Entire class disabled for due to architecture review.")
public class CreationProduitTransactionnelleIT {

  @Container
  @ServiceConnection
  static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:17.6");
  // TODO postgres:17.6 a mettre en EVM

  @Autowired
  Transaction transaction;

  @Autowired
  RepositoryJpaProduit repositoryJpaProduit;

  @Autowired
  GestionnaireDeProduit gestionnaireDeProduits;

  @Test
  public void annuler_les_modifications_si_le_cas_usage_echoue() {
    // Given
    Produit produit = ProduitFixtureBuilder.unBuilderAvecDonneesValides().build();
    var scenario = new ScenarioTransactionnel(transaction, gestionnaireDeProduits);

    assertThatThrownBy(() -> { scenario.executer(produit); })
        .isInstanceOf(ErreurSimulee.class);

    assertThat(repositoryJpaProduit.existsBySku(produit.sku().valeur()))
        .isFalse();
  }
}
