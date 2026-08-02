package web;

import application.ports.in.ForSayingHelloWorld;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class HelloWorldController {

  private final ForSayingHelloWorld forSayingHelloWorld;

  public HelloWorldController(ForSayingHelloWorld forSayingHelloWorld) {
    this.forSayingHelloWorld = forSayingHelloWorld;
  }

  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> sayHello() {
    forSayingHelloWorld.sayHelloUseCase();

    return ResponseEntity.of(Optional.of(Map.of("k", "Hello World")));
  }
}
