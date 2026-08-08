import core.lib.AdapterProvider;

module compositionroot {
  requires core;
  requires application;
  requires domain;
  requires web.adapter;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;

  // Services
  uses AdapterProvider;
}