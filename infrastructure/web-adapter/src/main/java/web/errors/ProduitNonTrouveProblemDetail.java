package web.errors;

import org.springframework.http.ProblemDetail;

import java.util.UUID;

public class ProduitNonTrouveProblemDetail extends ProblemDetail {

  private final String code;
  private final UUID idProduit;

  public ProduitNonTrouveProblemDetail(ProblemDetail problemDetail, UUID idProduit) {
    super(problemDetail);

    this.code = "PRODUIT_NON_TROUVE";
    this.idProduit = idProduit;
  }

  public String getCode() {
    return code;
  }

  public UUID getIdProduit() {
    return idProduit;
  }
}
