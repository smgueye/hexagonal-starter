package web;

import core.lib.Driver;
import application.ports.in.ForSayingHelloWorld;

public class ForSayingHelloWebUIDriver implements Driver {

  private static ForSayingHelloWorld forSayingHelloWorld;

  public ForSayingHelloWebUIDriver(ForSayingHelloWorld forSayingHelloWorld) {
    ForSayingHelloWebUIDriver.forSayingHelloWorld = forSayingHelloWorld;
  }

  public static ForSayingHelloWorld helloWorldTeller() {
    return ForSayingHelloWebUIDriver.forSayingHelloWorld;
  }

  @Override
  public void run(String... args) {
    SpringBootWebApp.main(args);
  }
}
