package web;

import com.github.app.application.commandes.CreationDeProduitCommand;
import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.usecases.PourGererLeCatalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import web.api.CatalogueApi;
import web.errors.ApiExceptionHandler;

@RestController
public class CatalogueController implements CatalogueApi {

  private static final Logger LOG = LoggerFactory.getLogger(CatalogueController.class);

  private final PourGererLeCatalogue gestionnaireDeCatalogue;

  public CatalogueController(PourGererLeCatalogue gestionnaireDeCatalogue) {
    this.gestionnaireDeCatalogue = gestionnaireDeCatalogue;
  }

  @Override
  public ResponseEntity<ReponseProduitCree> creerUnProduit(CreerProduitRequest request) throws ExceptionSkuDejaPresent {
    ProduitCree produitCree = gestionnaireDeCatalogue.creerUnProduit(new CreationDeProduitCommand(
        request.sku(),
        request.nom(),
        request.famille(),
        request.marque(),
        request.prixUnitaire(),
        request.attributsSpecifiques()));
    var response = new ReponseProduitCree(produitCree.id().value(), produitCree.sku().valeur(), produitCree.statut().type());
    LOG.info("Réponse HTTP={}", response);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(response);
  }
}
