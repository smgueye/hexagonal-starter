package com.github.smgueye.app.persistenceadapter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "catalogue", name = "produits")
public class EntiteJpaProduit implements Serializable {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true, length = 50)
  private String sku;

  @Column(nullable = false, length = 200)
  private String nom;

  @Column(nullable = false, length = 50)
  private String famille;

  @Column(nullable = false, length = 100)
  private String marque;

  @Column(name = "prix_unitaire", nullable = false, length = 50)
  private BigDecimal prixUnitaire;

  @Column(nullable = false, length = 20)
  private String statut;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "attributs_specifiques", nullable = false, columnDefinition = "jsonb")
  private Map<String, AttributJson> attributsSpecifiques;

  @Column(name = "cree_le", nullable = false)
  private Instant creeLe;

  @Column(name = "modifie_le")
  private Instant modifieLe;

  @Version
  private Long version;

  public UUID id() {
    return id;
  }

  public String sku() {
    return sku;
  }

  public String nom() {
    return nom;
  }

  public String famille() {
    return famille;
  }

  public String marque() {
    return marque;
  }

  public BigDecimal prixUnitaire() {
    return prixUnitaire;
  }

  public String statut() {
    return statut;
  }

  public Map<String, AttributJson> attributsSpecifiques() {
    return attributsSpecifiques;
  }

  public Instant creeLe() {
    return creeLe;
  }

  public Instant modifieLe() {
    return modifieLe;
  }
}
