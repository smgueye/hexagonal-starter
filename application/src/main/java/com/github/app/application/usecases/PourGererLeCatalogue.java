package com.github.app.application.usecases;

import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;

public interface PourGererLeCatalogue {

  ProduitCree creerUnProduit(CreationDeProduitCommand command) throws ExceptionSkuDejaPresent;
}
