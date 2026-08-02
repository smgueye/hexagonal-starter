package application.factory;

import application.App;
import application.ports.in.ForSayingHelloWorld;
import application.ports.out.ForObtainingXxx;

public interface AppService {

  static AppService getInstance(ForObtainingXxx forObtainingXxx) {
    return new App(forObtainingXxx);
  }

  ForSayingHelloWorld helloWorldTeller();
}
