#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
MODULE_PATH="$PROJECT_DIR/container/target/module-path"
CONFIG="$PROJECT_DIR/scripts/ports-adapters.properties"

java \
  --module-path "$MODULE_PATH" \
  --add-modules ALL-MODULE-PATH \
  --module container/container.Runner \
  "$CONFIG"