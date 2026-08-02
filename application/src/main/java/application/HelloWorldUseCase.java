package application;

import application.ports.in.ForSayingHelloWorld;
import application.ports.out.ForObtainingXxx;

public class HelloWorldUseCase implements ForSayingHelloWorld {

  private final ForObtainingXxx forObtainingXxx;

  public HelloWorldUseCase(ForObtainingXxx forObtainingXxx) {
    this.forObtainingXxx = forObtainingXxx;
  }

  @Override
  public void sayHelloUseCase() {
    System.out.println("Exec : hello world use case");
  }
}
