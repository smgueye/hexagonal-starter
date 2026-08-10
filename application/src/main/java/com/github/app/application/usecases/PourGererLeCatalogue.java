package com.github.app.application.usecases;

import com.github.app.application.commandes.ConsulterUnProduitCommand;
import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.resultats.ProduitDetail;

public interface PourGererLeCatalogue {

  ProduitCree creerUnProduit(CreationDeProduitCommand command) throws ExceptionSkuDejaPresent;

  ProduitDetail consulterUnProduit(ConsulterUnProduitCommand command);
}
