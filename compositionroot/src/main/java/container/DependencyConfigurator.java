package container;

import com.github.app.application.factory.CatalogueMicroservice;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;
import core.lib.Adapter;
import core.lib.Driver;
import web.WebDriverPourGererLeCatalogue;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class DependencyConfigurator {

  private final AdaptorSelector adapterSelector;

  public DependencyConfigurator(AdaptorSelector adapterSelector) {
    this.adapterSelector = adapterSelector;
  }

  public <T> T lookupDrivenPort(Class<T> drivenPortType) {
    String adapterName = adapterSelector.adapterNameForPort(drivenPortType);
    if (adapterName.isEmpty()) {
      return null;
    }
    List<ServiceLoader.Provider<T>> adapters = ServiceLoader
        .load(drivenPortType)
        .stream()
        .filter(p -> isDrivenAdapterOfName(p.type(), adapterName))
        .toList();

    if (adapters.size() > 1) {
      throw new RuntimeException("Many adapters with name '" + adapterName + "' found for driven port '"
          + drivenPortType.getSimpleName() + "'");
    }

    Optional<ServiceLoader.Provider<T>> adapter = adapters
        .stream()
        .findAny();
    if (adapter.isEmpty()) {
      throw new RuntimeException(
          "No adapter with name '" + adapterName + "' found for driven port '" + drivenPortType.getSimpleName() + "'");
    }

    return adapter.get().get();
  }

  private static <T> boolean isDrivenAdapterOfName(Class<? extends T> adapterType, String adapterName) {
    Adapter adapterAnnotation = adapterType.getAnnotation(Adapter.class);
    if ((adapterAnnotation == null) || (adapterAnnotation.name() == null))
      return false;

    return adapterAnnotation.name().equalsIgnoreCase(adapterName);
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
