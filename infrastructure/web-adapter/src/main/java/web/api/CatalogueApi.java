package web.api;

import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.CreerProduitRequest;
import web.ReponseProduitCree;

@Tag(name = "Catalogue", description = "Gestion des produits du catalogue")
@RequestMapping("api/v1/catalogue/produits")
public interface CatalogueApi {

  @Operation(
    operationId = "creerUnProduit",
    summary = "Créer un produit",
    description = "Crée un nouveau produit dans le catalogue.")
  @ApiResponses(
    @ApiResponse(
      responseCode = "201",
      description = "Produit créé avec succès",
      content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = ProduitCree.class))))
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ReponseProduitCree> creerUnProduit(@Valid @RequestBody CreerProduitRequest reponseProduitCreate) throws ExceptionSkuDejaPresent;
}
