#!/bin/bash

mongo <<EOF
rs.initiate({
 _id: "dbrs",
 members: [
   {_id: 0, host: "mongodb-data-1"},
   {_id: 1, host: "mongodb-data-2"},
   {_id: 2, host: "mongodb-data-3"}
 ]
})
rs.status();
EOF