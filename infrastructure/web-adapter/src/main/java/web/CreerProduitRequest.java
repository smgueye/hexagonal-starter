package web;

import com.github.app.domain.valueobject.Famille;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import web.validation.ValeurEnum;

import java.math.BigDecimal;

public record CreerProduitRequest(
    @NotBlank @Size(max = 50) String sku,
    @NotBlank @Size(max = 200) String nom,
    @NotBlank @ValeurEnum(Famille.class) String famille,
    @NotBlank @Size(max = 100) String marque,
    @NotNull @Positive BigDecimal prixUnitaire) {
}
