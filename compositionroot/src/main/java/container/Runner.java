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

    PourGererLesProduits gestionnaireDeProduits = dependencyConfigurator.lookupDrivenPort(PourGererLesProduits.class);


    CatalogueMicroservice app = dependencyConfigurator.buildApplication(gestionnaireDeProduits);
    AppInitializer.init(app);

    Driver pourGererLeCatalogue = dependencyConfigurator.lookupDriver(PourGererLeCatalogue.class, app);
    pourGererLeCatalogue.run();
  }
}