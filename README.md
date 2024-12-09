# Digital Course Marketplace Backend

A Spring Boot application that provides backend services for a digital course marketplace platform. The system supports three types of users (Admin, Creator, and Customer) with different functionalities for each role.

## Features

- User authentication and authorization with JWT
- Course management for creators
- Course purchasing for customers
- User management for admins
- Statistics tracking for course purchases
- Role-based access control
- Paginated API responses
- Search functionality for courses

## Technologies Used

- OpenJDK 21
- Spring Boot
- Spring Security
- Kotlin
- Spring Data JPA
- H2 Database
- Maven
- JWT for authentication
- Docker

## Prerequisites

- OpenJDK 21
- Maven 3.8+
- Docker (optional)

## Configuration

### Application Properties

Create `application.properties` file in `src/main/resources` with the following configurations:

```properties
# Server Configuration
spring:
  application:
  name: digital_course_marketplace_spring_boot_kotlin
  datasource:
    url: "jdbc:h2:file:~/digital_course_database;DB_CLOSE_ON_EXIT=FALSE"
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
   console:
     .enabled: true

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update

# JWT Configuration
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## Running the Application

### Using Maven

1. Clone the repository:
```bash
git clone https://github.com/abhaynm02/digital-course-marketplace-spring-boot-kotlin.git
```

2. Navigate to the project directory:
```bash
cd digital-course-marketplace
```

3. Build the application:
```bash
mvn clean package
```

4. Run the application:
```bash
java -jar target/digital-course-marketplace.jar
```

### Using Docker

1. Pull the Docker image:
```bash
docker pull abhaynm/spring-boot-kotlin-app:v1.0
```

2. Run the container:
```bash
docker run -p 8080:8080 abhaynm/spring-boot-kotlin-app:v1.0
```

## API Documentation

### Authentication APIs

#### Sign Up
```
POST /api/auth/signup
Body:
{
    "name": "User Name",
    "email": "user@example.com",
    "password": "password",
    "role": "CUSTOMER/CREATOR/ADMIN"
}
```

#### Sign In
```
POST /api/auth/signin
Body:
{
    "username": "user@example.com",
    "password": "password"
}
```

### Admin APIs

#### Get All Users
```
GET /api/admin/users?page=0&size=10
Header: Authorization: Bearer {token}
```

### Creator APIs

#### Create Course
```
POST /api/creator/create/course
Header: Authorization: Bearer {token}
Body:
{
    "title": "Course Title",
    "description": "Course Description",
    "price": 100
}
```

#### Get Creator Courses
```
GET /api/creator/get/courses?page=0&size=10
Header: Authorization: Bearer {token}
```

### Customer APIs

#### Get All Courses
```
GET /api/customer/courses?search={searchTerm}&page=0&size=10
Header: Authorization: Bearer {token}
```

#### Buy Course
```
POST /api/customer/course/buy/{courseId}
Header: Authorization: Bearer {token}
```

### Common APIs

#### Get Statistics
```
GET /api/stats/stats?startDate=2024-12-08&endDate=2024-12-30
Header: Authorization: Bearer {token}
```

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Success
- 201: Created
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error

## Security

- All endpoints except signup and signin require JWT authentication
- Passwords are hashed using BCrypt
- Role-based access control is implemented
- JWT token expiration is set to 24 hours

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details
