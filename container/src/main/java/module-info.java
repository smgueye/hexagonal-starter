import application.ports.out.ForObtainingXxx;

module container {
  requires core;
  requires application;
  requires persistence.adapter;
  requires web.adapter;

  // Services
  uses ForObtainingXxx;
}