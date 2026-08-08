package com.github.smgueye.app.persistenceadapter.integration;

import com.github.app.domain.PourGererLesProduits;
import com.github.app.domain.Produit;
import core.lib.Transaction;

public class ScenarioTransactionnel {

  private final Transaction transaction;

  private final PourGererLesProduits gestionnaireDeProduit;

  ScenarioTransactionnel(Transaction transaction, PourGererLesProduits gestionnaireDeProduits) {
    this.transaction = transaction;
    this.gestionnaireDeProduit = gestionnaireDeProduits;
  }

  Produit executer(Produit produit) throws Exception {
    return transaction.executer(() -> {
      gestionnaireDeProduit.enregistrer(produit);

      throw new ErreurSimulee("Erreur simulée");
    });
  }
}
