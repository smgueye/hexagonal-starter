package com.github.app.application;

import com.github.app.application.factory.CatalogueMicroservice;
import com.github.app.application.usecases.GestionnaireDeCatalogue;
import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;

public class AdapteurConfigurateur implements CatalogueMicroservice {

  // out.Port
  private PourGererLesProduits pourGererLesProduits;

  // in.Port
  private PourGererLeCatalogue pourGererLeCatalogue;

  public AdapteurConfigurateur(PourGererLesProduits pourGererLesProduits) {
    this.pourGererLesProduits = pourGererLesProduits;
  }

  @Override
  public PourGererLeCatalogue createurDeProduit() {
    if (this.pourGererLeCatalogue == null) {
      this.pourGererLeCatalogue = new GestionnaireDeCatalogue(pourGererLesProduits);
    }

    return this.pourGererLeCatalogue;
  }
}
