package container;


import com.github.app.domain.PourGererLesProduits;
import core.lib.AdapterProvider;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositionRootTest {

  @Test
  void selectionner_postgres_pour_gerer_les_produits() {

    Properties properties = new Properties();
    properties.setProperty(
      PourGererLesProduits.class.getSimpleName(),
      "postgres");

    var selector = new AdaptorSelector(properties);
    var configurator = new DependencyConfigurator(selector);

    AdapterProvider<?> provider = configurator.lookupProvider(PourGererLesProduits.class);

    assertThat(provider.port()).isEqualTo(PourGererLesProduits.class);
    assertThat(provider.name()).isEqualTo("postgres");
  }
}
