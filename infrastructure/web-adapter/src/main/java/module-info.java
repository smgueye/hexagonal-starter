open module web.adapter {
  requires core;
  requires application;
  requires spring.boot.autoconfigure;
  requires spring.boot;
  requires spring.context;
  requires spring.web;
  requires jakarta.validation;
  requires domain;
  requires spring.webmvc;
  requires org.jspecify;

  exports web;
}