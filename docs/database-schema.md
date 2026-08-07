## DDL

```sql
CREATE SCHEMA catalogue;

CREATE TABLE catalogue.produit (
id                  UUID           PRIMARY KEY,
sku                 VARCHAR(50)    NOT NULL,
nom                 VARCHAR(200)   NOT NULL,
famille             VARCHAR(50)    NOT NULL,
marque              VARCHAR(100)   NOT NULL,
prix_unitaire       NUMERIC(19, 2) NOT NULL,
statut              VARCHAR(20)    NOT NULL,
attributs_specifiques JSONB        NOT NULL DEFAULT '{}'::jsonb,
version             BIGINT         NOT NULL DEFAULT 0,
cree_le             TIMESTAMPTZ    NOT NULL,
modifie_le          TIMESTAMPTZ,

CONSTRAINT uk_produit_sku UNIQUE (sku),
CONSTRAINT ck_produit_prix_positif CHECK (prix_unitaire > 0),
CONSTRAINT ck_produit_statut
    CHECK (statut IN ('ACTIF', 'ARCHIVE'))
);
```

## Correspondance domaine

| Domaine               | PostgreSQL               |
| --------------------- | ------------------------ |
| `ProduitId`           | `UUID`                   |
| `Sku`                 | `VARCHAR(50)` + `UNIQUE` |
| `nom`                 | `VARCHAR(200)`           |
| `Famille`             | `VARCHAR(50)`            |
| `Marque`              | `VARCHAR(100)`           |
| `Argent`              | `NUMERIC(19,2)`          |
| `Statut`              | `VARCHAR(20)`            |
| `AttributsSpecifique` | `JSONB`                  |
| dates Java            | `TIMESTAMPTZ`            |
| concurrence           | `version BIGINT`         |

## _

### UUID généré par le domaine

```java
new ProduitId(UUID.randomUUID())
```

### SKU canonique

```java
this.valeur = valeur.strip().toUpperCase(Locale.ROOT);
```