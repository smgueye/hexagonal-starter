#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
MODULE_PATH="$PROJECT_DIR/compositionroot/target/module-path"
CONFIG="$PROJECT_DIR/scripts/ports-adapters.properties"

export CATALOGUE_DB_URL=jdbc:postgresql://localhost:5432/catalogue_service
export CATALOGUE_DB_USER=mgu
export CATALOGUE_DB_PASSWORD=mguX123

java \
  --module-path "$MODULE_PATH" \
  --add-modules ALL-MODULE-PATH \
  --module compositionroot/container.Runner \
  "$CONFIG"