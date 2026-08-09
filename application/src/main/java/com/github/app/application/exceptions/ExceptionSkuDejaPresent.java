package com.github.app.application.exceptions;

public final class ExceptionSkuDejaPresent extends Exception {

  private final String sku;

  public ExceptionSkuDejaPresent(String sku) {
    super("Le SKU existe déjà " + sku);

    this.sku = sku;
  }

  public String sku() {
    return sku;
  }
}
