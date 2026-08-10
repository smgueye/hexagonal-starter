package com.github.app.application.resultats;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record ProduitDetail(UUID id, String sku, String nom, String famille, String marque, BigDecimal prixUnitaire,
                            String statut, Map<String, String> attributs) {
}
