#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER NOMOSNOW;
    CREATE DATABASE sporteventorganization;
    GRANT ALL PRIVILEGES ON DATABASE docker TO NOMOSNOW;
EOSQL