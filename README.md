# amotask Backend

A Spring Boot backend API for managing basic day-to-day tasks. This project connects to a PostgreSQL database and provides endpoints for creating, updating, retrieving, and deleting tasks, as well as managing nested follow-up tasks.

## Features

- **CRUD for Tasks**: Create, retrieve, update, and delete tasks.
- **Mark as Complete**: Endpoints to mark both tasks and follow-up tasks as completed.

## Technologies Used

- **Spring Boot**
- **PostgreSQL**
- **JPA and Hibernate**

## Getting Started

### Prerequisites

- Java 21 or later
- PostgreSQL
- Maven

### Installation

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

### API Endpoints

| Method   | Endpoint                      | Description                              |
|----------|--------------------------------|------------------------------------------|
| `POST`   | `/tasks`                       | Create a new task                        |
| `GET`    | `/tasks`                       | Get all tasks                            |
| `GET`    | `/tasks/{id}`                  | Get a task by ID                         |
| `PUT`    | `/tasks/{id}`                  | Update a task by ID                      |
| `DELETE` | `/tasks/{id}`                  | Delete a task by ID                      |
| `PUT`    | `/tasks/{id}/complete`         | Mark a task as complete                  |


### Sample Request and Response

Here's an example of creating a new task:

- **Request**:
    ```json
    POST /tasks
    Content-Type: application/json

    {
      "title": "Sample Task",
      "description": "This is a sample task",
      "dueDate": "2024-12-01"
    }
    ```

- **Response**:
    ```json
    {
      "id": 1,
      "title": "Sample Task",
      "description": "This is a sample task",
      "dueDate": "2024-12-01",
      "completed": false
    }
    ```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to contribute by submitting issues or pull requests!
