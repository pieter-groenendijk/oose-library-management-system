FROM postgres:16.3-alpine3.20

COPY _docker/database/scripts/ /docker-entrypoint-initdb.d/

EXPOSE 5432