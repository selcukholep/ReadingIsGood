#!/bin/bash

docker-compose up -d

sleep 5

docker exec mongodb-data-1 /scripts/rs-init.sh