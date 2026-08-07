package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.PourGererLesProduits;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class PersistenceAdapterContext implements AutoCloseable{

  private final ConfigurableApplicationContext context;

  public PersistenceAdapterContext(ConfigurableApplicationContext context) {
    this.context = context;
  }

  public static PersistenceAdapterContext demarrer(){
    ConfigurableApplicationContext context = new SpringApplicationBuilder(PersistenceAdapterContext.class)
      .web(WebApplicationType.NONE)
      .properties("spring.config.name=persistence-adapter") // pour que les config soient indépendqntes
      .run();
    return new PersistenceAdapterContext(context);
  }

  public PourGererLesProduits gestionnaireDeProduits() {
    return context.getBean(PourGererLesProduits.class);
  }

  @Override
  public void close() throws Exception {
    context.close();
  }
}
