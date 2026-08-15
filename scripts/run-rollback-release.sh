#!/usr/bin/env bash

TAG=$1

./mvnw release:rollback -DpushChanges=false
git push origin --delete "$TAG"
git tag -d "$TAG"