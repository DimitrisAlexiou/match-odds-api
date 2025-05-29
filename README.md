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

| Field       | Type        | Description                          |
|-------------|-------------|--------------------------------------|
| `id`        | Long        | Unique identifier for the match      |
| `description` | String    | Description (e.g., PAO-OSFP)         |
| `match_date` | Date       | Date of the match                    |
| `match_time` | String     | Time of the match                    |
| `team_a`    | String      | Home team                            |
| `team_b`    | String      | Away team                            |
| `sport`     | Enum (int)  | 1 = Football, 2 = Basketball         |

---

### 🎲 MatchOdds

| Field       | Type    | Description                          |
|-------------|---------|--------------------------------------|
| `id`        | Long    | Unique identifier for the odds       |
| `match_id`  | Long    | Reference to a match                 |
| `specifier` | String  | Bet type (e.g., 1, X, 2)             |
| `odd`       | Double  | Betting odd                          |

---

## 💡 Example

### Match
```json
{
  "id": 1,
  "description": "PAO-OSFP",
  "match_date": "2025-03-31",
  "match_time": "21:00",
  "team_a": "PAO",
  "team_b": "OSFP",
  "sport": 1
}
