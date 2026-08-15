#!/usr/bin/env bash

RELEASE_VERSION=$1
DEV_VERSION=$2

echo "RELEASE_VERSION=v$RELEASE_VERSION"
echo "DEV_VERSION=$DEV_VERSION"

./mvnw -B release:prepare \                                                                                                                                                                         ─╯
  -DreleaseVersion="v$RELEASE_VERSION" \
  -DdevelopmentVersion="$DEV_VERSION"-SNAPSHOT \
  -Dtag="$RELEASE_VERSION" \
  -DpushChanges=false
git push origin master
git push origin "$RELEASE_VERSION"
