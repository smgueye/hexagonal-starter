package application;

import application.factory.AppService;
import application.ports.in.ForSayingHelloWorld;
import application.ports.out.ForObtainingXxx;

public class App implements AppService {

  // out.Port
  private ForObtainingXxx forObtainingXxx;

  // in.Port
  private ForSayingHelloWorld forSayingHelloWorld;

  public App(ForObtainingXxx forObtainingXxx) {
    this.forObtainingXxx = forObtainingXxx;
  }

  @Override
  public ForSayingHelloWorld helloWorldTeller() {
    if (this.forSayingHelloWorld == null) {
      this.forSayingHelloWorld = new HelloWorldUseCase(forObtainingXxx);
    }

    return this.forSayingHelloWorld;
  }
}
