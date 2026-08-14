#!/usr/bin/env bash

# docker run --rm \
#  --add-host=host.docker.internal:host-gateway \
#  -i grafana/k6 run - < catalogue-read.js

docker run --rm --name estore -p 5432:5432 -v postgres_data:/var/lib/postgresql/data -d postgres:14-alpine    

docker run --rm -d \
  --name prometheus \
  --add-host=host.docker.internal:host-gateway \
  -p 9090:9090 \
  -v "$PWD/observability/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml:ro" \
  prom/prometheus:v3.13.2

docker run -d --rm --name jaeger -p 16686:16686 -p 4317:4317 -p 4318:4318 cr.jaegertracing.io/jaegertracing/jaeger:2.20.0

docker run -d --name grafana -p 3000:3000 grafana/grafana

docker run -d --name loki -v "$PWD:/mnt/config" -p 3100:3100 grafana/loki:3.7.0 -config.file=/mnt/config/loki-config.yaml

docker run -d \
    --name alloy \
    --add-host=host.docker.internal:host-gateway \
    -p 12345:12345 \
    -v "$PWD/observability/alloy/config.alloy:/etc/alloy/config.alloy:ro" \
    -v "$PWD/logs:/var/log/catalogue:ro" grafana/alloy:latest \
  run --server.http.listen-addr=0.0.0.0:12345 \
    --storage.path=/var/lib/alloy/data /etc/alloy/config.alloy
