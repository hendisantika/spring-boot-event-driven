# spring-boot-event-driven

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-event-driven/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-event-driven/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-event--driven-231F20?logo=apachekafka)

Sample **event-driven microservices** built with Spring Boot and Apache Kafka.
`order-service` stores an order and publishes an `ORDER_CREATED` event; `payment-service`
consumes that event and records a payment.

## Architecture

```
            POST /api/orders
  client ───────────────────▶ order-service (:8080)
                                   │  save Order (H2: ordersdb)
                                   │  publish OrderEvent
                                   ▼
                          Kafka topic: orders-topic
                                   │
                                   ▼
                          payment-service (:8081)
                                      save Payment (H2: paymentsdb)
```

## Tech Stack

| Component     | Version / Library                                  |
|---------------|----------------------------------------------------|
| Java          | 25 (Temurin in CI)                                 |
| Spring Boot   | 4.1.x (`webmvc`, `data-jpa`, `kafka`, `h2console`) |
| JSON          | Jackson 3 (`tools.jackson`, `JacksonJsonSerializer`) |
| Messaging     | Apache Kafka                                       |
| Database      | H2 in-memory                                       |
| Build         | Maven Wrapper (per service)                        |
| Others        | Lombok, DevTools                                   |

## Project Structure

```
.
├── order-service      # REST API + Kafka producer (port 8080)
├── payment-service    # Kafka consumer (port 8081)
└── .github
    ├── workflows/maven.yml   # CI: build & test each service with JDK 25
    └── dependabot.yml        # Grouped Maven & GitHub Actions updates
```

## Prerequisites

- JDK 25
- Docker (to run Kafka locally) or an existing Kafka broker on `localhost:9092`

## Running Locally

1. Start Kafka (single-node KRaft):

   ```bash
   docker run -d --name kafka -p 9092:9092 apache/kafka:latest
   ```

2. Start the services (in separate terminals):

   ```bash
   cd order-service && ./mvnw spring-boot:run
   cd payment-service && ./mvnw spring-boot:run
   ```

3. Create an order:

   ```bash
   curl -X POST http://localhost:8080/api/orders \
     -H "Content-Type: application/json" \
     -d '{
           "item": "Book",
           "quantity": 2,
           "amount": 150000,
           "paymentMode": "CREDIT_CARD",
           "address": "Bandung"
         }'
   ```

4. Check the data in the H2 consoles:

   | Service         | URL                                | JDBC URL                 |
   |-----------------|------------------------------------|--------------------------|
   | order-service   | http://localhost:8080/h2-console   | `jdbc:h2:mem:ordersdb`   |
   | payment-service | http://localhost:8081/h2-console   | `jdbc:h2:mem:paymentsdb` |

## Build & Test

```bash
cd order-service && ./mvnw verify
cd payment-service && ./mvnw verify
```

## CI/CD

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests `order-service` and
  `payment-service` in parallel (matrix) on JDK 25 for every push / pull request to `main`.
- **Dependabot** (`.github/dependabot.yml`) checks Maven dependencies daily and GitHub Actions
  weekly (05:00 Asia/Jakarta), grouping related updates (Spring Boot, Spring, Kafka, test,
  Maven plugins, minor/patch) into fewer pull requests.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
