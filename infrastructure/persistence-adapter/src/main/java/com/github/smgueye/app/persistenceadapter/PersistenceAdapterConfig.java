package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.PourGererLesProduits;
import core.lib.Transaction;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackageClasses = RepositoryJpaProduit.class)
@EntityScan(basePackageClasses = EntiteJpaProduit.class)
public class PersistenceAdapterConfig {
  // Note au lieu de component et de repository, l'adaptateur déclare
  // explicitement ce dont il a besoin.

  @Bean
  public MapperDePersistenceProduit mapperDePersistenceProduit() {
    return new MapperDePersistenceProduit();
  }

  @Bean
  public PourGererLesProduits gestionnaireDeProduits(RepositoryJpaProduit repository,
      MapperDePersistenceProduit mapper) {
    return new GestionnaireDeProduit(mapper, repository);
  }

  @Bean
  Transaction transaction(PlatformTransactionManager transactionManager) {
    return new TransactionSpring(transactionManager);
  }
}
