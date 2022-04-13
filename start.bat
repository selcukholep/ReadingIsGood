#!/bin/bash

docker-compose up -d

sleep 10

docker exec mongodb-data-1 /scripts/rs-init.sh

