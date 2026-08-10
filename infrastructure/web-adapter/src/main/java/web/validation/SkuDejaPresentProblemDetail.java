package web.validation;

import org.springframework.http.ProblemDetail;

public class SkuDejaPresentProblemDetail extends ProblemDetail {

  private final String code;
  private final String sku;

  public SkuDejaPresentProblemDetail(ProblemDetail problemDetail, String sku) {
    super(problemDetail);

    this.code = "SKU_DEJA_PRESENT";
    this.sku = sku;
  }

  public String getCode() {
    return code;
  }

  public String getSku() {
    return sku;
  }
}
