package container;

import com.github.app.application.factory.CatalogueMicroservice;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration(proxyBeanMethods = false)
public class ApplicationConfiguration {

  @Bean
  CatalogueMicroservice catalogueMicroservice(PourGererLesProduits gestionnaireDeProduits) {
    return CatalogueMicroservice.getInstance(gestionnaireDeProduits);
  }

  @Bean
  PourGererLeCatalogue gestionnaireDeCatalogue(CatalogueMicroservice catalogueMicroservice) {
    return catalogueMicroservice.createurDeProduit();
  }
}
