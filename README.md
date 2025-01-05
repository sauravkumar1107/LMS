# Lead Management System

## 1. Project Overview
The Lead Management System is a B2B solution designed for Key Account Managers (KAMs) at Udaan to efficiently manage restaurant leads. The system provides comprehensive features for tracking leads, managing contacts, monitoring interactions, and analyzing account performance.

### Key Features
- Restaurant lead management
- Multiple contact points tracking
- Interaction history recording
- Call scheduling and reminders
- Performance metrics and analytics
- KAM transfer management

## 2. System Requirements
- Java 11 or higher
- Docker and Docker Compose
- PostgreSQL 15 or higher
- Git

## 3. Installation Instructions

### Clone the Repository
```bash
git clone https://github.com/sauravkumar1107/LMS.git
cd lms
```

### Configure Database
The application uses PostgreSQL running in Docker. The database configuration is handled through the docker-compose.yml file.

### Test data
Test data for all the tables are present in "resources/data.sql". When service boots up, all the tables are dropped and created again with the defined data present in data.sql

### Build the Application
```bash
mvn clean install
```

## 4. Running Instructions

### Using Docker Compose
1. Start the application and database:
```bash
docker-compose -f .devcontainer/docker-compose.yml build
docker-compose -f .devcontainer/docker-compose.yml up
```

2. Check the logs:
```bash
docker-compose logs -f app
```

3. Stop the application:
```bash
docker-compose down
```

### Environment Variables
Configure the following environment variables or update application.properties:
```properties
spring.application.name=lms
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.path=/api-docs
```

## 5. Test Execution Guide

### Running Unit Tests
```bash
mvn test
```

### Running Integration Tests
```bash
mvn verify
```

### Test Coverage Report
```bash
mvn clean test jacoco:report
```
The coverage report will be available at `target/site/jacoco/index.html`

## 6. API Documentation

### Swagger UI
Access the API documentation through Swagger UI:
- URL: https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/swagger-ui.html ``Change baseUrl as per the application in thr OpenAPIConfig's servers``

### Main API Endpoints

#### Restaurant Management
- `POST /api/v1/restaurant` - Create new restaurant lead
- `GET /api/v1/restaurant/kam/{id}` - Fetch all restaurant leads of a KAM 
- `GET /api/v1/restaurant/{id}/contacts` - Get all contacts of a restaurant
- `PUT /api/v1/restaurant/{id}` - Update restaurant details (kamId, contacts, name, address, etc.)

#### Order Management
- `POST /api/v1/order` - Place new order

#### Interaction Tracking
- `POST /api/v1/interaction` - Record new interaction
- `GET /api/v1/interaction/kam/{id}` - Find all calls scheduled/planned for today for a KAM

#### Performance Metrics
- `GET /api/v1/restaurant/performance/kam/{id}/{period}/{inc}` - Get top or worst performing restaurants under a KAM
- `GET /api/v1/order/restaurant/{id}/{period}` - Get all orders placed by a restaurant in a given period

## 7. Sample Usage Examples

### Creating a New Restaurant Lead
```curl
curl -X 'POST' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/restaurant' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "kamId": "kam123",
  "name": "Taj Mahal",
  "address": "Rohini, Delhi-110023",
  "starsRating": 0,
  "frequency": "DAILY",
  "contacts": [
    {
      "name": "Ashok",
      "role": "MANAGER",
      "phone": "87874785875",
      "email": "ashok@gmail.com"
    }
  ]
}'
```

### Fetch all restaurant leads of a KAM
```curl
curl -X 'GET' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/restaurant/kam/kam123' \
  -H 'accept: */*'
```

### Get all contacts of a restaurant
```curl
curl -X 'GET' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/restaurant/36c98b77-ae24-4de5-aaa4-895df193bff6/contacts' \
  -H 'accept: */*'
```

### Update restaurant details, in this example KAM
```curl
curl -X 'PUT' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/restaurant/36c98b77-ae24-4de5-aaa4-895df193bff6' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "kamId": "kam456"
}'
```

### Place new order
```curl
curl -X 'POST' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/order' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "restId": "REST001",
  "restBuyerId": "CONT001",
  "purchasedProducts": [
    {
      "productId": "EQUIP001",
      "quantity": 2,
      "unitPrice": 89999
    }
  ],
  "totalPrice": 179998
}'
```

### Record new interaction
```curl
curl -X 'POST' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/interaction' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "restId": "REST002",
  "kamId": "KAM001",
  "contactId": "CONT002"
}'
```

### Find all calls scheduled/planned for today for a KAM
```curl
curl -X 'GET' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/interaction/kam/KAM002' \
  -H 'accept: */*'
```

### Get top or worst performing restaurants under a KAM
```curl
curl -X 'GET' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/restaurant/performance/kam/KAM001/WEEK/1' \
  -H 'accept: */*'
```

### Get all orders placed by a restaurant in a given period
```curl
curl -X 'GET' \
  'https://expert-rotary-phone-9jj46gwvww5f74xp-8080.app.github.dev/api/v1/order/restaurant/REST001/WEEK' \
  -H 'accept: */*'
```