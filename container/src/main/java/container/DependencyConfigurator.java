package container;

import application.factory.AppService;
import application.ports.in.ForSayingHelloWorld;
import application.ports.out.ForObtainingXxx;
import core.lib.Adapter;
import core.lib.Driver;
import web.ForSayingHelloWebUIDriver;

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

  public AppService buildApplication(ForObtainingXxx xxxProvider) {
    return AppService.getInstance(xxxProvider);
  }

  public Driver lookupDriver(Class<ForSayingHelloWorld> driverPortType, AppService app) {
    String adapterName = adapterSelector.adapterNameForPort(driverPortType);
    if (AdaptorSelector.SPRING_WEB.equals(adapterName)) {
      return new ForSayingHelloWebUIDriver(app.helloWorldTeller());
    }

    throw new RuntimeException("No driver found for driver port '" + driverPortType.getSimpleName() + "'");
  }
}
