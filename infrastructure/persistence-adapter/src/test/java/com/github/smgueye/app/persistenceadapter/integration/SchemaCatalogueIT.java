package com.github.smgueye.app.persistenceadapter.integration;

import com.github.smgueye.app.persistenceadapter.PersistenceAdapterConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import javax.sql.DataSource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@SpringBootTest(
    classes = PersistenceAdapterConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    properties = "spring.config.name=persistence-adapter"
)
@Disabled("Entire class disabled for due to architecture review.")
public class SchemaCatalogueIT {

  @Container
  @ServiceConnection
  static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:17.6");
  // TODO postgres:17.6 a mettre en EVM

  @Autowired
  private DataSource dataSource;

  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void setUp() {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Test
  void contrainte_prix_unitaire_positif() {
    UUID id = UUID.randomUUID();
    String sku = "TEST-" + UUID.randomUUID();

    assertThatThrownBy(() ->
      jdbcTemplate.update("""
        INSERT INTO catalogue.produits (id, sku, nom, famille, marque, prix_unitaire, statut, 
                                        attributs_specifiques, version, cree_le)
        VALUES (?, ?, ?, ?, ?, ?, ?, '{}'::jsonb, ?, now())""",
        id, sku, "Produit test", "TEST", "TEST", -10, "ACTIF", 0)
    ).isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  void contrainte_sur_statut() {
    UUID id = UUID.randomUUID();
    String sku = "TEST-" + UUID.randomUUID();

    assertThatThrownBy(() ->
      jdbcTemplate.update("""
        INSERT INTO catalogue.produits (id, sku, nom, famille, marque, prix_unitaire, statut, 
                                        attributs_specifiques, version, cree_le)
        VALUES (?, ?, ?, ?, ?, ?, ?, '{}'::jsonb, ?, now())""",
            id, sku, "Produit test", "TEST", "TEST", 10, "XXX", 0 )
    ).isInstanceOf(DataIntegrityViolationException.class);
  }
}
