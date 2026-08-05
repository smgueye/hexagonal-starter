module application {
  requires domain;
  requires core;

  exports com.github.app.application.factory to container;
  exports com.github.app.application.commandes to container;

  // Driving port
  exports com.github.app.application to web.adapter, container;
  exports com.github.app.application.usecases to container, web.adapter;
  exports com.github.app.application.resultats to web.adapter;
}
