package web;

import application.ports.in.ForSayingHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebApp {

  public static void main(String[] args) {
    new SpringApplicationBuilder(SpringBootWebApp.class)
      .web(WebApplicationType.SERVLET)
      .run(args);
  }

  @Bean
  public ForSayingHelloWorld helloWorldTeller() {
    return ForSayingHelloWebUIDriver.helloWorldTeller();
  }
}
