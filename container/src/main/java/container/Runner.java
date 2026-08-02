package container;

import application.factory.AppService;
import application.ports.in.ForSayingHelloWorld;
import application.ports.out.ForObtainingXxx;
import core.lib.Driver;

import java.io.IOException;

public class Runner {
  public static void main(String[] args) throws IOException {
    AdaptorSelector adaptorSelector = AdaptorSelector.fromFile(args[0]);
    DependencyConfigurator dependencyConfigurator = new DependencyConfigurator(adaptorSelector);

    ForObtainingXxx xxxProvider = getForObtainingXxx(dependencyConfigurator);

    AppService app = dependencyConfigurator.buildApplication(xxxProvider);
    AppInitializer.init(app);

    Driver forDoingXxx = dependencyConfigurator.lookupDriver(ForSayingHelloWorld.class, app);
    forDoingXxx.run();
  }

  private static ForObtainingXxx getForObtainingXxx(DependencyConfigurator dependencyConfigurator) {
    ForObtainingXxx xxxProvider = dependencyConfigurator.lookupDrivenPort(ForObtainingXxx.class);
    return xxxProvider;
  }
}