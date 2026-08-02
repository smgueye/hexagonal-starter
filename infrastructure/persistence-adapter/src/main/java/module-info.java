import application.ports.out.ForObtainingXxx;

module persistence.adapter {
  requires core;
  requires application;

  exports persistence;

  provides ForObtainingXxx with persistence.StubXxxProviderAdapter;
}