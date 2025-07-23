# amotask Backend

A Spring Boot backend API for managing tasks and follow-up tasks. This project connects to a PostgreSQL database and provides endpoints for creating, updating, retrieving, and deleting tasks, as well as managing nested follow-up tasks.

## Features

- **CRUD for Tasks**: Create, retrieve, update, and delete tasks.
- **Follow-Up Tasks**: Support for creating follow-up tasks and nested follow-up tasks for each task.
- **Mark as Complete**: Endpoints to mark both tasks and follow-up tasks as completed.

## Technologies Used

- **Spring Boot**
- **PostgreSQL**
- **JPA and Hibernate**

## Getting Started

### Prerequisites

- Java 11 or later
- PostgreSQL (with a database named `taskdb`)
- Maven

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/NarjeeshTP/amotask-backend.git
    cd amotask-backend
    ```

2. Set up the PostgreSQL database:
    - Ensure PostgreSQL is running.
    - Create a database called `taskdb` (or modify `application.properties` accordingly).
    - Update the `spring.datasource.username` and `spring.datasource.password` in `src/main/resources/application.properties` with your PostgreSQL credentials.

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
| `POST`   | `/tasks/{taskId}/follow-up`    | Create a follow-up task                  |
| `GET`    | `/tasks/{id}/follow-up`        | Get follow-up tasks for a specific task  |
| `PUT`    | `/follow-up/{id}`              | Update a follow-up task                  |
| `DELETE` | `/follow-up/{id}`              | Delete a follow-up task                  |
| `POST`   | `/follow-up/{followUpTaskId}/follow-up` | Create a nested follow-up task |
| `GET`    | `/follow-up/{id}/follow-ups`   | Get nested follow-up tasks               |

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
