FROM eclipse-temurin:21-jre-alpine

LABEL authors="smgueye"

WORKDIR /app
RUN mkdir -p /app/module-path

COPY scripts/ports-adapters.properties /app/ports-adapters.properties
COPY container/target/container-*.jar /app/module-path/
COPY container/target/module-path/*.jar /app/module-path/

EXPOSE 8080

ENTRYPOINT ["java", "--module-path", "/app/module-path",  "--add-modules", "ALL-MODULE-PATH", "--module", "container/container.CatalogueApplication", "/app/ports-adapters.properties"]
