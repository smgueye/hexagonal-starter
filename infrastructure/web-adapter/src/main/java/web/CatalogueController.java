package web;

import com.github.app.application.usecases.PourGererLeCatalogue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class CatalogueController {

  private final PourGererLeCatalogue pourGererLeCatalogue;

  public CatalogueController(PourGererLeCatalogue pourGererLeCatalogue) {
    this.pourGererLeCatalogue = pourGererLeCatalogue;
  }

  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> sayHello() {
    // TODO

    return ResponseEntity.of(Optional.of(Map.of("k", "Hello World")));
  }
}
