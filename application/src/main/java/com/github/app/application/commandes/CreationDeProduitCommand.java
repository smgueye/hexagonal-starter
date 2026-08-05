package com.github.app.application.commandes;

import com.github.app.domain.valueobject.Famille;
import com.github.app.domain.valueobject.attributsspecifiques.AttributsSpecifique;

import java.math.BigDecimal;
import java.util.Map;

public record CreationDeProduitCommand(
    String sku,
    String nom,
    String famille,
    String marque,
    BigDecimal prixUnitaire,
    Map<String, Map<String, String>> attributsSpecifiques) {
}
