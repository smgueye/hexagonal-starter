module application {

  exports application.factory to container;

  // Driving port
  exports application.ports.in to web.adapter, container;

  // Driven port
  exports application.ports.out to persistence.adapter, container;
}
