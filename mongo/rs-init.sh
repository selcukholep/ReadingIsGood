#!/bin/bash

mongo <<EOF
var config = {
    "_id": "dbrs",
    "version": 1,
    "members": [
        {
            "_id": 1,
            "host": "mongodb-data-1:27017",
            "priority": 3
        },
        {
            "_id": 2,
            "host": "mongodb-data-2:27017",
            "priority": 2
        },
        {
            "_id": 3,
            "host": "mongodb-data-3:27017",
            "priority": 1
        }
    ]
};
rs.initiate(config, { force: true });
rs.status();
EOF