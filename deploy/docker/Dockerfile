FROM eclipse-temurin:21-jammy

ARG VERSION
ARG VCS_REF
ARG SOURCE_URL

LABEL org.opencontainers.image.title="catalogue-service" \
      org.opencontainers.image.version="${VERSION}" \
      org.opencontainers.image.revision="${VCS_REF}" \
      org.opencontainers.image.source="${SOURCE_URL}"

WORKDIR app

ARG APP_UID=10001
ARG APP_GID=10001

RUN groupadd --gid ${APP_GID} catalogue \
  && useradd \
    --uid ${APP_UID} \
    --gid ${APP_GID} \
    --no-create-home \
    --shell /usr/sbin/nologin \
    catalogue

COPY --chown=${APP_UID}:${APP_GID} \
     compositionroot/target/catalogue-service-${VERSION}.jar \
     /app/catalogue-service.jar

USER ${APP_UID}:${APP_GID}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/catalogue-service.jar"]
