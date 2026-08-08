package container;

import com.github.app.domain.PourGererLesProduits;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class BranchementPostgresIT {

  @Container
  static final PostgreSQLContainer POSTGRES = new PostgreSQLContainer("postgres:17.6");

  @Test
  void brancher_reellement_l_adaptateur_postgres() {

    System.setProperty("CATALOGUE_DB_URL", POSTGRES.getJdbcUrl());
    System.setProperty("CATALOGUE_DB_USER", POSTGRES.getUsername());
    System.setProperty("CATALOGUE_DB_PASSWORD", POSTGRES.getPassword());

    try {
      Properties properties = new Properties();
      properties.setProperty(
          PourGererLesProduits.class.getSimpleName(),
          "postgres");

      var selector = new AdaptorSelector(properties);
      var configurator = new DependencyConfigurator(selector);

      PourGererLesProduits adaptateur = configurator.lookupDrivenPort(PourGererLesProduits.class);
      assertThat(adaptateur).isNotNull();
    } finally {
      System.clearProperty("CATALOGUE_DB_URL");
      System.clearProperty("CATALOGUE_DB_USER");
      System.clearProperty("CATALOGUE_DB_PASSWORD");
    }
  }
}
