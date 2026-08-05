package container;

import com.github.app.application.factory.CatalogueMicroservice;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;
import core.lib.Driver;

import java.io.IOException;

public class Runner {
  public static void main(String[] args) throws IOException {
    AdaptorSelector adaptorSelector = AdaptorSelector.fromFile(args[0]);
    DependencyConfigurator dependencyConfigurator = new DependencyConfigurator(adaptorSelector);

    PourGererLesProduits xxxProvider = getForObtainingXxx(dependencyConfigurator);

    CatalogueMicroservice app = dependencyConfigurator.buildApplication(xxxProvider);
    AppInitializer.init(app);

    Driver forDoingXxx = dependencyConfigurator.lookupDriver(PourGererLeCatalogue.class, app);
    forDoingXxx.run();
  }

  private static PourGererLesProduits getForObtainingXxx(DependencyConfigurator dependencyConfigurator) {
    PourGererLesProduits xxxProvider = dependencyConfigurator.lookupDrivenPort(PourGererLesProduits.class);
    return xxxProvider;
  }
}