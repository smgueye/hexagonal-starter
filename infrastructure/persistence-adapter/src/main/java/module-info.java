import com.github.app.domain.PourGererLesProduits;
import persistence.ProduitEnMemoire;

module persistence.adapter {
  requires core;
  requires application;
  requires domain;

  exports persistence;

  provides PourGererLesProduits with ProduitEnMemoire;
}