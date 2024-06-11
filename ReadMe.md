# Todo App

This is a Todo application built using React, TypeScript, Tailwind CSS, and Spring Boot. The app allows users to create, update, and delete tasks, categorize them, and mark them as complete or incomplete.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Running the App](#running-the-app)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Create Tasks**: Add new tasks with a title, description, and category.
- **Update Tasks**: Edit task titles and descriptions.
- **Delete Tasks**: Remove tasks from the list.
- **Categorize Tasks**: Organize tasks by categories.
- **Mark as Complete/Incomplete**: Toggle tasks between complete and incomplete statuses.

## Prerequisites

Before you begin, ensure you have the following installed:

- [Node.js](https://nodejs.org/) (v14 or higher)
- [npm](https://www.npmjs.com/) (v6 or higher)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (v11 or higher)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)

## Installation

### Backend Setup

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/dalia3aly/ToDo.git
   cd todo-app/backend

2. **Create MySQL Database:**:
   Create a MySQL database named todo_db.
   CREATE DATABASE todo_db;

3. **Update Application Properties**:
- Update the `src/main/resources/application.properties` file with your MySQL database credentials.

  ```sh
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
Build and Run the Backend:

  ```sh

mvn spring-boot:run