package web.errors;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {
    List<ErreurChamp> erreurs = exception.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> {
        String codeErreur = ValidationCodeErreur.chercherParCodeContrainte(error.getCode()).name();
        return new ErreurChamp(
          error.getField(),
          codeErreur,
          error.getDefaultMessage()
        );
      })
      .toList();

    ProblemDetail probleme = ProblemDetail.forStatusAndDetail(status, "Un ou plusieurs champs sont invalides.");
    probleme.setTitle("Requête invalide");
    probleme.setType(URI.create("https://api.catalogue/problems/validation"));
    probleme.setProperty("erreurs", erreurs);

    return handleExceptionInternal(exception, probleme, headers, status, request);
  }
}
