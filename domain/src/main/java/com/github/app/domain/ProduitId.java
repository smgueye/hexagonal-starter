package com.github.app.domain;

import core.lib.BaseId;

import java.util.UUID;

public final class ProduitId extends BaseId<UUID> {

  public ProduitId(UUID value) {
    super(value);
  }
}
