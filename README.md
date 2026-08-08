pricing-service/
├── domain/
│   ├── src/main/java/
│   └── src/test/java/
│
├── application/
│   ├── src/main/java/
│   └── src/test/java/
│
├── adapters/
│   ├── in/
│   │   ├── rest-spring/
│   │   │   ├── src/main/java/
│   │   │   └── src/test/java/
│   │   └── rest-quarkus/
│   │
│   └── out/
│       ├── persistence-memory/
│       └── persistence-postgresql/
│
├── bootstrap/
│   ├── spring-boot/
│   └── quarkus/
│
├── architecture-tests/
├── acceptance-tests/
└── test-support/


TODO
Invariant
---
SKU unique ;
prix strictement positif ;
statut limité à ACTIF ou ARCHIVE ;
aucun type JPA dans le domaine ;
aucun identifiant auto-incrémenté ;
dates stockées avec fu seau horaire.

TODO : Test optimistic
ArchUnit
--

./mvnw -pl infrastructure/persistence-adapter -am clean package