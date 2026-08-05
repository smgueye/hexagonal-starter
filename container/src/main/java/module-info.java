import com.github.app.domain.PourGererLesProduits;

module container {
  requires core;
  requires application;
  requires domain;
  requires persistence.adapter;
  requires web.adapter;

  // Services
  uses PourGererLesProduits;
}