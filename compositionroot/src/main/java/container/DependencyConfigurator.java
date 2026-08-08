package container;

import com.github.app.application.factory.CatalogueMicroservice;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;
import core.lib.AdapterProvider;
import core.lib.Driver;
import web.WebDriverPourGererLeCatalogue;

import java.util.ServiceLoader;

public class DependencyConfigurator {

  private final AdaptorSelector adapterSelector;

  public DependencyConfigurator(AdaptorSelector adapterSelector) {
    this.adapterSelector = adapterSelector;
  }

  public AdapterProvider<?> lookupProvider(Class<?> drivenPortType) {
    String adapterName = adapterSelector.adapterNameForPort(drivenPortType);
    if (adapterName == null && adapterName.isBlank()) {
      throw new RuntimeException("Aucun adaptateur configure pour le port " + drivenPortType);
    }

    var providers = ServiceLoader
      .load(AdapterProvider.class)
      .stream()
      .map(ServiceLoader.Provider::get)
      .filter(provider -> provider.port().equals(drivenPortType))
      .filter(provider -> provider.name().equalsIgnoreCase(adapterName))
      .toList();

    if (providers.size() > 1) {
      throw new RuntimeException("Many adapters with name '" + adapterName + "' found for driven port '"
          + drivenPortType.getSimpleName() + "'");
    }

    if (providers.isEmpty()) {
      throw new RuntimeException(
          "No adapter with name '" + adapterName + "' found for driven port '" + drivenPortType.getSimpleName() + "'");
    }

    return providers.getFirst();
  }

  public <T> T lookupDrivenPort(Class<T> drivenPortType) {
    AdapterProvider<?> provider = lookupProvider(drivenPortType);

    Object adapter = provider.create();

    return drivenPortType.cast(adapter);
  }

  public CatalogueMicroservice buildApplication(PourGererLesProduits xxxProvider) {
    return CatalogueMicroservice.getInstance(xxxProvider);
  }

  public Driver lookupDriver(Class<PourGererLeCatalogue> driverPortType, CatalogueMicroservice app) {
    String adapterName = adapterSelector.adapterNameForPort(driverPortType);
    if (AdaptorSelector.SPRING_WEB.equals(adapterName)) {
      return new WebDriverPourGererLeCatalogue(app.createurDeProduit());
    }

    throw new RuntimeException("No driver found for driver port '" + driverPortType.getSimpleName() + "'");
  }
}
