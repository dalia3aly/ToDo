# Todo App

This is a Todo API built with Java Spring Boot. The API allows users to get, create, update, and delete tasks, categorize them, and mark them as complete or incomplete.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the App](#running-the-app)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Contributing](#contributing)

## Features

- **Create Tasks**: Add new tasks with a title, description, and category.
- **Update Tasks**: Edit task titles and descriptions.
- **Delete Tasks**: Remove tasks from the list.
- **Categorize Tasks**: Organize tasks by categories.
- **Mark as Complete/Incomplete**: Toggle tasks between complete and incomplete statuses.

## Prerequisites

Before you begin, ensure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (v11 or higher)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)

## Installation

1. **Clone the Repository**:

   ```sh
   git clone https://github.com/dalia3aly/ToDo-App-BE.git
   cd todo-app/backend

   ```

2. **Create MySQL Database:**:
   Create a MySQL database named todo_db.
   CREATE DATABASE todo_db;

3. **Update Application Properties**:

- Update the `src/main/resources/application.properties` file with your MySQL database credentials.

```
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

```

4. **Build and Run the Backend**:
   mvn spring-boot:run

## Running the App

To run the Todo App, follow these steps:

1. **Clone the Repository**:

```sh
git clone https://github.com/dalia3aly/ToDo-App-BE.git
```

2. **Create MySQL Database**:
   Create a MySQL database named `todo_db`:

```sql
CREATE DATABASE todo_db;
```

3. **Update Application Properties**:
   Update the `src/main/resources/application.properties` file with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

4. **Build and Run the Backend**:
   Run the following command to build and run the backend:

```sh
mvn spring-boot:run
```

5. **Start the Frontend**:

Please refer to the frontend github repo:

```
https://github.com/dalia3aly/ToDo-App-FE
```

## Usage

Once the Todo App is up and running, you can perform the following actions:

- Create a new task by clicking on the "Add Task" button and filling in the required details.
- Update a task by clicking on the task card and editing the title or description.
- Delete a task by clicking on the "Delete" button on the task card.
- Categorize tasks by selecting a category from the dropdown menu on the task card.
- Mark a task as complete or incomplete by clicking on the checkbox next to the task title.

## Folder Structure

The folder structure of the Todo API is as follows:

```
todo-app/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── todo/
│   │   │   │           ├── category/
│   │   │   │           │   ├── Category.java
│   │   │   │           │   ├── CategoryController.java
│   │   │   │           │   ├── CategoryRepository.java
│   │   │   │           │   ├── CategoryService.java
│   │   │   │           │   └── CreateCategoryDTO.java
│   │   │   │           ├── common/
│   │   │   │           │   └── BaseEntity.java
│   │   │   │           ├── config/
│   │   │   │           │   ├── CorsConfig.java
│   │   │   │           │   └── ModelMapperConfig.java
│   │   │   │           ├── exceptions/
│   │   │   │           │   ├── CategoryNotFoundException.java
│   │   │   │           │   ├── GlobalExceptionalHandler.java
│   │   │   │           │   └── NotFoundException.java
│   │   │   │           ├── todoapp/
│   │   │   │           │   ├── CreateTodoDTO.java
│   │   │   │           │   ├── Todo.java
│   │   │   │           │   ├── TodoController.java
│   │   │   │           │   ├── TodoRepository.java
│   │   │   │           │   ├── TodoService.java
│   │   │   │           │   └── UpdateTodoDTO.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── static/
│   │   └── test/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── todo/
│   │       │           ├── category/
│   │       │           │   ├── CategoryControllerTest.java
│   │       │           │   ├── CategoryRepositoryTest.java
│   │       │           │   └── CategoryServiceTest.java
│   │       │           └── todoapp/
│   │       │               ├── TodoControllerTest.java
│   │       │               ├── TodoRepositoryTest.java
│   │       │               └── TodoServiceTest.java
│   └── pom.xml
└── frontend/
   ├── public/
   └── src/
      ├── components/
      ├── pages/
      ├── services/
      └── App.js
```

## Contributing

Contributions are welcome! If you would like to contribute to the Todo App, please follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Commit your changes.
5. Push to the branch.
6. Open a pull request.

- Please ensure that your code follows the project's coding style and conventions.

**Thank you for your interest in contributing to the Todo App!**
