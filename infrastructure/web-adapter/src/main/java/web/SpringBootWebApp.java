package web;

import com.github.app.application.usecases.PourGererLeCatalogue;
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
  public PourGererLeCatalogue helloWorldTeller() {
    return ForSayingHelloWebUIDriver.helloWorldTeller();
  }
}
