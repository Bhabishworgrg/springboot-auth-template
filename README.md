# Springboot JWT Authentication Template

A starter Spring Boot project with secure JWT-based authentication. Ideal for building RESTful APIs with user registration and login out of the box.

---

## Features
- User registration with password hashing
- Login endpoint with JWT token issuance
- Spring Security integration
- DTO-based request handling
- Modular project structure for scalability
- Ready-to-use for real projects or learning

## Usage
1. Clone the repository.
2. Create a PostgreSQL database.
3. Update `application.yaml` in `src/main/resources` with your credentials.
```yaml
jwt:
  secret-key: <your_secret_key>
server:
  error:
    include-messages: always
    include-binding-errors: always
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/<your_database_name>
    username: <your_database_username>
    password: <your_database_password>
  jpa:
    hibernate:
      ddl-auto: update 
    show-sql: true
```
4. Build the project.
```bash
./gradlew build
```
5. Run the application.
```bash
./gradlew bootRun
```

## Endpoints

### GET: homepage
```url
http://localhost:8080/
```

### POST: register
```url
http://localhost:8080/register
```

**Body**
```json
{
    "username": "test",
    "email": "test@email.com",
    "password": "password123"
}
```

### POST: login
```url
http://localhost:8080/login
```

**Body**
```json
{
    "email": "test@email.com",
    "password": "password123"
}
```

### GET: test secured endpoint
```url
http://localhost:8080/test/secured
```
- Requires JWT token as Bearer token.

---

# License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

# Get in Touch

[<img src="https://img.shields.io/badge/email-white?&style=for-the-badge&logo=gmail" alt="Email"/>](mailto:bhabishworgrg@gmail.com)
[<img src="https://img.shields.io/badge/linkedin-blue?&style=for-the-badge" alt="LinkedIn"/>](https://www.linkedin.com/in/bhabishwor-gurung/)
