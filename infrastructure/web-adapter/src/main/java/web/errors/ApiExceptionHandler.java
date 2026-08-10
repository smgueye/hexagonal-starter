package web.errors;

import com.github.app.application.exceptions.ExceptionProduitNonTrouve;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

import com.github.app.application.exceptions.ExceptionSkuDejaPresent;
import web.validation.ValidationCodeErreur;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException exception,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    var httpRequest = ((ServletWebRequest) request).getRequest();

    LOG.error(
      "Impossible d'écrire la réponse HTTP method={} path={} cause={}",
      httpRequest.getMethod(),
      httpRequest.getRequestURI(),
      exception.getMostSpecificCause().getMessage(),
      exception);

    return super.handleHttpMessageNotWritable(
      exception,
      headers,
      status,
      request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    HttpServletRequest httpRequest = ((ServletWebRequest) request).getRequest();
    LOG.warn("Requête HTTP invalide method={} path={} cause={}",
      httpRequest.getMethod(),
      httpRequest.getRequestURI(),
      exception.getMostSpecificCause().getMessage());

    return super.handleHttpMessageNotReadable(exception, headers, status, request);
  }

  @Override
  protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatusCode status,
                                                                          @NonNull WebRequest request) {
    List<ErreurChamp> erreurs = exception.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> {
        String codeErreur = ValidationCodeErreur.chercherParCodeContrainte(error.getCode()).name();
        return new ErreurChamp(
          error.getField(),
          codeErreur,
          error.getDefaultMessage());
      })
      .toList();

    ProblemDetail probleme = ProblemDetail.forStatusAndDetail(status, "Un ou plusieurs champs sont invalides.");
    probleme.setTitle("Requête invalide");
    probleme.setType(URI.create("https://api.catalogue/problems/validation"));
    ValidationProblemDetail validationProbleme = new ValidationProblemDetail(probleme, erreurs);

    return handleExceptionInternal(exception, validationProbleme, headers, status, request);
  }

  @ExceptionHandler(ExceptionSkuDejaPresent.class)
  ProblemDetail handleSkuDejaPresent(ExceptionSkuDejaPresent exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Un produit avec ce SKU existe déjà.");
    problem.setTitle("SKU déjà utilisé");
    problem.setType(URI.create("https://api.catalogue/problems/sku-already-exists"));

    return new SkuDejaPresentProblemDetail(problem, exception.sku());
  }

  @ExceptionHandler(ExceptionProduitNonTrouve.class)
  ProblemDetail handleProduitNonTrouve(ExceptionProduitNonTrouve exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Le produit est introuvable.");
    problem.setTitle("Produit introuvable");
    problem.setType(URI.create("https://api.catalogue/problems/produit-introuvable"));

    return new ProduitNonTrouveProblemDetail(problem, exception.getProduitId());
  }
}
