package web;

import com.github.app.domain.valueobject.Famille;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import web.validation.ValeurEnum;

import java.math.BigDecimal;
import java.util.Map;

public record CreerProduitRequest(
    @NotBlank(message = "{catalogue.produit.sku.required}")
    @Size(max = 50, message = "{catalogue.produit.sku.max-size}")
    String sku,

    @NotBlank(message = "{catalogue.produit.nom.required}")
    @Size(max = 200, message = "{catalogue.produit.nom.max-size}")
    String nom,

    @NotBlank(message = "{catalogue.produit.famille.required}")
    @ValeurEnum(value = Famille.class, message = "{catalogue.produit.famille.invalid}")
    String famille,

    @NotBlank(message = "{catalogue.produit.marque.required}")
    @Size(max = 100, message = "{catalogue.produit.marque.max-size}")
    String marque,

    @NotNull(message = "{catalogue.produit.prix-unitaire.required}")
    @Positive(message = "{catalogue.produit.prix-unitaire.positive}")
    BigDecimal prixUnitaire,

    Map<String, String> attributsSpecifiques) {
}
