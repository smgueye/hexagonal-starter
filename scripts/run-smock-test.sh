#!/usr/bin/env bash

# Executer en bash avant de pousser dans la CI

./mvnw --batch-mode --no-transfer-progress verify

VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
GIT_SHA=$(git rev-parse HEAD)
SOURCE_URL=$(git remote get-url origin)

NETWORK_NAME="catalogue-ci"

docker build \
  -f deploy/docker/Dockerfile \
  --build-arg VERSION="$VERSION" \
  --build-arg VCS_REF="$GIT_SHA" \
  --build-arg SOURCE_URL="$SOURCE_URL" \
  -t catalogue-service:"$VERSION" \
  .

if [ -z "$(docker network ls --filter name=^${NETWORK_NAME}$ --format '{{.Name}}')" ]; then
  echo "Le réseau n'existe pas. Création en cours..."
  docker network create "$NETWORK_NAME"
else
  echo "Le réseau existe déjà."
fi

docker run --rm -d \
  --name catalogue-database \
  --network "$NETWORK_NAME" \
  -e POSTGRES_DB=catalogue_service \
  -e POSTGRES_USER=catalogue \
  -e POSTGRES_PASSWORD=catalogue \
  postgres:16

for i in {1..30}; do
  if docker exec catalogue-database \
    pg_isready -U catalogue -d catalogue_service; then
    break
  fi
  sleep 1
done

docker run -d \
  --name catalogue-service \
  --network "$NETWORK_NAME"\
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:postgresql://catalogue-database:5432/catalogue_service \
  -e DB_USER=catalogue \
  -e DB_PASSWORD=catalogue \
  -e DB_MAX_POOL_SIZE=10 \
  -e DB_CONNECTION_TIMEOUT=30000 \
  -e LIQUIBASE_ENABLED=true \
  -e TRACE_HOST=http://host.docker.internal:4318 \
  catalogue-service:"$VERSION"

for i in {1..30}; do
  if curl --fail --silent \
    http://localhost:8080/actuator/health/readiness; then
    exit 0
  fi
  sleep 2
done

# clean up
docker container stop catalogue-service
docker container rm catalogue-service
docker container stop catalogue-database
docker network rm catalogue-ci
