package com.github.app.application.factory;

import com.github.app.application.AdapteurConfigurateur;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;

public interface CatalogueMicroservice {

  static CatalogueMicroservice getInstance(PourGererLesProduits pourGererLesProduits) {
    return new AdapteurConfigurateur(pourGererLesProduits);
  }

  PourGererLeCatalogue createurDeProduit();
}
