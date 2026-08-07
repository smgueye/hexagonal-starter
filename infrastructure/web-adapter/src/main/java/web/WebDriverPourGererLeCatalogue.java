package web;

import core.lib.Driver;
import com.github.app.application.usecases.PourGererLeCatalogue;

public class WebDriverPourGererLeCatalogue implements Driver {

  private static PourGererLeCatalogue pourGererLeCatalogue;

  public WebDriverPourGererLeCatalogue(PourGererLeCatalogue pourGererLeCatalogue) {
    WebDriverPourGererLeCatalogue.pourGererLeCatalogue = pourGererLeCatalogue;
  }

  public static PourGererLeCatalogue gestionnaireDeCatalogue() {
    return WebDriverPourGererLeCatalogue.pourGererLeCatalogue;
  }

  @Override
  public void run(String... args) {
    SpringBootWebApp.main(args);
  }
}
