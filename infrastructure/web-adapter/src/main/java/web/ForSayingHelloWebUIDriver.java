package web;

import core.lib.Driver;
import com.github.app.application.usecases.PourGererLeCatalogue;

public class ForSayingHelloWebUIDriver implements Driver {

  private static PourGererLeCatalogue pourGererLeCatalogue;

  public ForSayingHelloWebUIDriver(PourGererLeCatalogue pourGererLeCatalogue) {
    ForSayingHelloWebUIDriver.pourGererLeCatalogue = pourGererLeCatalogue;
  }

  public static PourGererLeCatalogue helloWorldTeller() {
    return ForSayingHelloWebUIDriver.pourGererLeCatalogue;
  }

  @Override
  public void run(String... args) {
    SpringBootWebApp.main(args);
  }
}
