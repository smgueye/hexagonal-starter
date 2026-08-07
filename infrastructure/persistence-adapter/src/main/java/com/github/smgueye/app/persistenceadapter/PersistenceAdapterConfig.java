package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.PourGererLesProduits;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = RepositoryJpaProduit.class)
@EntityScan(basePackageClasses = EntiteJpaProduit.class)
public class PersistenceAdapterConfig {
  // Note au lieu de component et de repository, l'adaptateur déclare explicitement ce dont il a besoin.

  @Bean
  public MapperDePersistenceProduit mapperDePersistenceProduit() {
    return new MapperDePersistenceProduit();
  }

  @Bean
  public PourGererLesProduits gestionnaireDeProduits(RepositoryJpaProduit repository,
                                                     MapperDePersistenceProduit mapper) {
    return new GestionnaireDeProduit(mapper, repository);
  }
}
// /db/changelog/db.changelog-master.xml