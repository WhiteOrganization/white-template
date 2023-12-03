#!/usr/bin/env sh

DOCKER_COMPOSE_FILE=src/main/docker/docker-compose-it.yml

_docker_compose_get_image_name() {
    image_name=$(fgrep --no-filename --max-count=1 image "${DOCKER_COMPOSE_FILE}" | cut -d':' -f2-)
    # remove leading whitespace characters
    image_name="${image_name#"${image_name%%[![:space:]]*}"}"
    # remove trailing whitespace characters
    image_name="${image_name%"${image_name##*[![:space:]]}"}"
    echo "${image_name}"
}

_docker_compose_pull_image() {
    docker image inspect "${1}" 1>/dev/null
    image_exist=$?
    if [ $image_exist != 0 ]; then
        echo '[INFO] Pull IT Docker images...'
        docker compose -f "${DOCKER_COMPOSE_FILE}" pull
    fi
}

# We don't want that the images download affect to the timeout,
# so pull images before up if they are not in the local image repository
image_name=$(_docker_compose_get_image_name)
_docker_compose_pull_image "${image_name}"

echo '[INFO] Up IT Docker containers...'
expect <<EOD
set timeout 5
spawn docker compose -f "${DOCKER_COMPOSE_FILE}" up
expect "database system is ready to accept connections"
EOD