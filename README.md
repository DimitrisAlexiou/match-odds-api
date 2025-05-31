# Spring-REST-Web-API

RESTful Web API for managing sports matches and their associated betting odds

## 📌 Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## 🎯 Objective

Develop a backend system that allows the creation, retrieval, and management of sports matches and their corresponding betting odds.

## 📚 Domain Model

### 🏟️ Match

| Field         | Type       | Description                     |
| ------------- | ---------- | ------------------------------- |
| `id`          | Long       | Unique identifier for the match |
| `description` | String     | Description (e.g., PAO-OSFP)    |
| `match_date`  | Date       | Date of the match               |
| `match_time`  | String     | Time of the match               |
| `team_a`      | String     | Home team                       |
| `team_b`      | String     | Away team                       |
| `sport`       | Enum (int) | 1 = Football, 2 = Basketball    |

---

### 🎲 MatchOdds

| Field       | Type   | Description                    |
| ----------- | ------ | ------------------------------ |
| `id`        | Long   | Unique identifier for the odds |
| `match_id`  | Long   | Reference to a match           |
| `specifier` | String | Bet type (e.g., 1, X, 2)       |
| `odd`       | Double | Betting odd                    |

---

## 💡 Example

### Match

```json
{
  "description": "PAO-OSFP",
  "match_date": "2025-03-31",
  "match_time": "21:00",
  "team_a": "PAO",
  "team_b": "OSFP",
  "sport": 1
}
```
# MatchOdds API - Dockerized Setup

This project provides a fully containerized Spring Boot REST API with a PostgreSQL database using Docker Compose.

## Prerequisites

- [Docker](https://www.docker.com/get-started) and [Docker Compose](https://docs.docker.com/compose/) installed
- Java 21 and Maven (for building the jar)

## Quick Start

```
### 1. Build the Spring Boot Jar

```

./mvnw clean package or mvn clean install -DskipTests

```

### 2. Start the App and Database with Docker Compose

```

docker-compose up --build

```

- The API will be available at: [http://localhost:8080](http://localhost:8080)
- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- PostgreSQL will be available on port **55432** (localhost:55432)

### 3. Connect to the Database (optional)

```

You can connect to the database using any PostgreSQL client:

- **Host:** `localhost`
- **Port:** `55432`
- **Database:** `matchodds`
- **Username:** `postgres`
- **Password:** `postgres`

```

### 4. Stopping and Cleaning Up

To stop the containers:

```

docker-compose down

```

To remove volumes/data as well:

```

docker-compose down -v

```

## Tips

- The app will wait for the database to be ready before starting.
- You can change the exposed ports in `docker-compose.yml` if needed.
- Logs for the app and database can be viewed with `docker-compose logs`.

---

```
