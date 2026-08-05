import com.github.app.domain.PourGererLesProduits;

module domain {
  requires core;

  exports com.github.app.domain;
  exports com.github.app.domain.valueobject;
  exports com.github.app.domain.valueobject.attributsspecifiques;
  exports com.github.app.domain.exceptions;
}