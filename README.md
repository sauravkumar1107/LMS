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

### Build the Application
```bash
mvn clean install
```

## 4. Running Instructions

### Using Docker Compose
1. Start the application and database:
```bash
docker-compose up -d
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
- URL: http://localhost:8080/swagger-ui.html ``Change baseUrl as per the application``

### Main API Endpoints

#### Restaurant Management
- `POST /api/v1/restaurant` - Create new restaurant lead
- `GET /api/v1/restaurant/kam/{id}` - Fetch all restaurant leads of a KAM 
- `GET /api/v1/restaurant/{id}/contacts` - Get all contacts of a restaurant
- `PUT /api/v1/restaurant/{id}` - Update restaurant details

#### Contact Management
- `POST /api/v1/order` - Place new order

#### Interaction Tracking
- `POST /api/v1/interaction` - Record new interaction
- `GET /api/v1/interaction/kam/{id}` - Find all calls scheduled/planned for today for a KAM

#### Performance Metrics
- `GET /api/v1/restaurant/performance/kam/{id}/{count}/{order}` - Get top or worst performing restaurants under a KAM
- `GET /api/v1/order/restaurant/{id}/{period}` - Get all orders placed by a restaurant in a given period

## 7. Sample Usage Examples

### Creating a New Restaurant Lead
```curl
curl -X POST http://localhost:8080/api/v1/leads/restaurants \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pizza Palace",
    "address": "123 Main Street",
    "city": "Mumbai",
    "state": "Maharashtra",
    "timezone": "Asia/Kolkata"
  }'
```

### Adding a Contact
```curl
curl -X POST http://localhost:8080/api/v1/leads/restaurants/1/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "role": "OWNER",
    "phone": "+91-9876543210",
    "email": "john@pizzapalace.com",
    "isPrimary": true
  }'
```

### Recording an Interaction
```curl
curl -X POST http://localhost:8080/api/v1/leads/restaurants/1/interactions \
  -H "Content-Type: application/json" \
  -d '{
    "type": "CALL",
    "contactId": 1,
    "notes": "Discussed menu expansion plans"
  }'
```

### Getting Performance Metrics
```curl
curl -X GET http://localhost:8080/api/v1/leads/restaurants/1/performance?days=30
```