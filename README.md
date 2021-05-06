## Rest API

Build REST API with Spring Boot, JDBC and H2DB.

## Requirements

1. Java - 11.x.x

2. Maven - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/diogo09git/rest-api-with-JDBC.git
```

**2. Build and run the app using maven**

```bash
mvn package
java -jar target/api-rest-0.0.1-SNAPSHOT.jar
```

**3. Alternatively, you can run the app without packaging it using**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/products
    
    GET /api/products/{id}
    
    POST /api/products
    
    DELETE /api/products/{id}
    
    PUT /api/products/{id}

#### You can test them using Postman or any other rest client.

