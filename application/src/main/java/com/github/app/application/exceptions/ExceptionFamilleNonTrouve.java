package com.github.app.application.exceptions;

public final class ExceptionFamilleNonTrouve extends RuntimeException {

  private final String famille;

  public ExceptionFamilleNonTrouve(String famille) {
    super("La famille " + famille + " est invalide");

    this.famille = famille;
  }

  public String famille() {
    return famille;
  }
}
