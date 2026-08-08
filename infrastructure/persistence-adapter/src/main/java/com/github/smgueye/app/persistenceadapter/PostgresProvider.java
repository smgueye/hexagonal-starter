package com.github.smgueye.app.persistenceadapter;

import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import core.lib.Adapter;
import core.lib.AdapterProvider;

import java.util.Optional;

public class PostgresProvider implements AdapterProvider<PourGererLesProduits> {

  @Override
  public Class<PourGererLesProduits> port() {
    return PourGererLesProduits.class;
  }

  @Override
  public String name() {
    return "postgres";
  }

  @Override
  public PourGererLesProduits create() {
    return PersistenceAdapterContext
      .demarrer()
      .gestionnaireDeProduits();
  }
}
