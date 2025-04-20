# Employee API

This is a Spring Boot REST API project with the following features:

- Role-based access control using Spring Security and JWT
- Hibernate ORM for persistence
- One-to-Many relationships (Employee → Wife, Children)
- PostgreSQL as the database
- Roles:
  - USER: can read and edit employees
  - ADMIN: can read, edit, and delete employees

## Setup

1. Ensure you have PostgreSQL installed and running.
2. Create a database named `employee_db`.
3. Update the database username and password in `src/main/resources/application.properties`.
4. Build the project using Maven:

```bash
mvn clean install
```

5. Run the application:

```bash
mvn spring-boot:run
```

## API Endpoints

- `POST /auth/login` - Authenticate and get JWT token
- `GET /employees` - Get all employees (USER, ADMIN)
- `GET /employees/{id}` - Get employee by ID (USER, ADMIN)
- `POST /employees` - Create new employee (USER, ADMIN)
- `PUT /employees/{id}` - Update employee (USER, ADMIN)
- `DELETE /employees/{id}` - Delete employee (ADMIN only)

## Notes

- Use the JWT token in the `Authorization` header as `Bearer <token>` for secured endpoints.
- Passwords are currently stored as plain text for demonstration; replace with encoded passwords in production.
