import com.github.app.domain.PourGererLesProduits;

module compositionroot {
  requires core;
  requires application;
  requires domain;
  requires persistence.adapter;
  requires web.adapter;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;

  // Services
  uses PourGererLesProduits;
}