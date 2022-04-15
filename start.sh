#!/bin/bash

docker network create reading-is-good-network

docker-compose -f mongo/docker-compose.yml up -d

echo "Waiting 5 seconds..."
sleep 5

docker exec -it mongodb-data-1 /scripts/rs-init.sh

echo "Waiting 20 seconds..."
sleep 20

#mvn clean install

docker-compose up --build -d

echo "OK"