package web;

import com.github.app.domain.valueobject.Statut;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record ReponseDetailDuProduit(UUID id, String sku, String nom, String famille, String marque, BigDecimal prixUnitaire,
                                     String statut, Map<String, String> attributs) {
}
