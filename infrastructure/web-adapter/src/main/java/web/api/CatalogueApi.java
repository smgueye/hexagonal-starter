package web.api;

import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import com.github.app.application.resultats.ProduitCree;
import com.github.app.application.resultats.ProduitDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.CreerProduitRequest;
import web.ReponseDetailDuProduit;
import web.ReponseProduitCree;
import web.errors.ProduitNonTrouveProblemDetail;
import web.errors.SkuDejaPresentProblemDetail;
import web.errors.ValidationProblemDetail;

import java.util.UUID;

@Tag(name = "Catalogue", description = "Gestion des produits du catalogue")
@RequestMapping("api/v1/catalogue/produits")
public interface CatalogueApi {

  @Operation(
    operationId = "creerUnProduit",
    summary = "Créer un produit",
    description = "Crée un nouveau produit dans le catalogue.")
  @ApiResponses({
    @ApiResponse(
      responseCode = "201",
      description = "Produit créé avec succès",
      content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = ProduitCree.class))),

    @ApiResponse(
      responseCode = "400",
      description = "Requête invalide",
      content = @Content(
        mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
        schema = @Schema(implementation = ValidationProblemDetail.class))),

      @ApiResponse(
        responseCode = "409",
        description = "Un produit avec ce SKU existe déjà",
        content = @Content(
          mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
          schema = @Schema(implementation = SkuDejaPresentProblemDetail.class)))
  })
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ReponseProduitCree> creerUnProduit(@Valid @RequestBody CreerProduitRequest reponseProduitCreate) throws ExceptionSkuDejaPresent;

  @Operation(
    operationId = "consulterUnProduit",
    summary = "Consulter un produit",
    description = "Consultation des détails d'un produit du catalogue")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200",
      description = "Les détails du produit ont été extrait avec succès.",
      content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = ProduitDetail.class))),
      @ApiResponse(
        responseCode = "400",
        description = "Requête invalide.",
        content = @Content(
          mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
          schema = @Schema(implementation = ValidationProblemDetail.class))),
      @ApiResponse(
        responseCode = "404",
        description = "Le produit est introuvable.",
        content = @Content(
          mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
          schema = @Schema(implementation = ProduitNonTrouveProblemDetail.class))),
  })
  @GetMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ReponseDetailDuProduit> consulterUnProduit(@Valid @RequestParam("produitId") UUID produitId);
}
