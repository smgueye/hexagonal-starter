module domain {
  requires core;
  exports com.github.app.domain to application;
  exports com.github.app.domain.valueobject to application;
  exports com.github.app.domain.valueobject.attributsspecifiques to application;
  exports com.github.app.domain.exceptions to application;
}