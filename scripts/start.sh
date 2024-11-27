#!/bin/bash

PROJECT_DIRECTORY=$(realpath "$(dirname "$0")/..")

cd "${PROJECT_DIRECTORY}/application" || exit

sudo docker compose up --build --watch
sudo docker compose down --remove-orphans

cd -