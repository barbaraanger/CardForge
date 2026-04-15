COMPOSE_FILE := docker-compose.yml
DOCKER_COMPOSE := docker compose -f $(COMPOSE_FILE)

.PHONY: help up down restart logs ps reset

help:
	@echo "Available commands:"
	@echo "  make up       - Start local infrastructure (Postgres + Kafka)"
	@echo "  make down     - Stop and remove infrastructure containers"
	@echo "  make restart  - Restart infrastructure containers"
	@echo "  make logs     - Tail infrastructure logs"
	@echo "  make ps       - List running compose services"
	@echo "  make reset    - Stop containers and remove volumes"

up:
	$(DOCKER_COMPOSE) up -d

down:
	$(DOCKER_COMPOSE) down

restart:
	$(DOCKER_COMPOSE) down
	$(DOCKER_COMPOSE) up -d

logs:
	$(DOCKER_COMPOSE) logs -f

ps:
	$(DOCKER_COMPOSE) ps

reset:
	$(DOCKER_COMPOSE) down -v --remove-orphans