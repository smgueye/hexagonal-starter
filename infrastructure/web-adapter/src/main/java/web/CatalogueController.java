package web;

import com.github.app.application.usecases.PourGererLeCatalogue;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("catalogue/produits")
public class CatalogueController {

  private final PourGererLeCatalogue pourGererLeCatalogue;

  public CatalogueController(PourGererLeCatalogue pourGererLeCatalogue) {
    this.pourGererLeCatalogue = pourGererLeCatalogue;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Map<String, String>> create(@Valid @RequestBody CreerProduitRequest request) {
    // TODO

    return ResponseEntity.of(Optional.of(Map.of("k", "Hello World")));
  }
}
