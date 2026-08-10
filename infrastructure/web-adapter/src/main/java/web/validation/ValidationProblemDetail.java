package web.validation;

import org.springframework.http.ProblemDetail;
import web.errors.ErreurChamp;

import java.util.List;

public class ValidationProblemDetail extends ProblemDetail {

  private final List<ErreurChamp> errors;

  public ValidationProblemDetail(
      ProblemDetail problemDetail,
      List<ErreurChamp> errors) {
    super(problemDetail);

    this.errors = errors;
  }

  public List<ErreurChamp> getErrors() {
    return errors;
  }
}
