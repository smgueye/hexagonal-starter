package persistence;

import com.github.app.domain.Produit;
import com.github.app.domain.ProduitId;
import com.github.app.domain.valueobject.Sku;
import core.lib.Adapter;

import com.github.app.domain.PourGererLesProduits;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Adapter(name = "test-double")
public class ProduitEnMemoire implements PourGererLesProduits {

  private final Map<ProduitId, Produit> produits = new HashMap<>();

  @Override
  public ProduitId uneIdentite() {
    return new ProduitId(UUID.randomUUID());
  }

  @Override
  public boolean existeAvecUnSku(Sku sku) {
    // TODO
    return false;
  }

  @Override
  public void creerUnProduit(Produit produit) {
    produits.put(produit.id(), produit);
  }

  @Override
  public Optional<Produit> rechercherUnProduitParId(ProduitId produitId) {
    return Optional.ofNullable(produits.get(produitId));
  }

  @Override
  public void enregistrer(Produit produit) {
    // TODO
  }
}
