MONGODB_USER=rodrigohf
MONGODB_PASSWORD=LVtoCRpMcg3N04h3

.PHONY: build
build:
	./mvnw clean package
	docker build -t alura-linguagens-api .

.PHONY: up
up:
	MONGODB_USER=$(MONGODB_USER) MONGODB_PASSWORD=$(MONGODB_PASSWORD) docker-compose up -d

.PHONY: down
down:
	docker-compose down
