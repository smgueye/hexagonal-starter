module application {
  requires domain;
  requires core;
  requires org.slf4j;

  exports com.github.app.application.factory to compositionroot;
  exports com.github.app.application.commandes to compositionroot, web.adapter;

  // Driving port
  exports com.github.app.application to web.adapter, compositionroot;
  exports com.github.app.application.usecases to compositionroot, web.adapter;
  exports com.github.app.application.resultats to web.adapter;
  exports com.github.app.application.exceptions;
}
