# AI-assisted Software Engineering. Java. Practical Tasks

## Task with low level complexity

## Description
Create a RESTful API to manage a simple todo list application using Spring Boot, Hibernate, and MySQL.
The application should allow users to create, read, update, and delete todo items. Each item should have a title and a description.
Use Hibernate to persist the items in the database.

### Running the program
1. Clone this repository:
   ```sh
   git clone https://github.com/your-username/repository-name.git
2. Install MySQL (if not installed). Create a database, a user and grant them privileges:
3. Configure the database:
   ```sh
   CREATE DATABASE new_database;
   CREATE USER 'new_user'@'localhost' IDENTIFIED BY 'new_password';
   GRANT CREATE, SELECT, INSERT, UPDATE, DELETE ON new_database.* TO 'new_user'@'localhost';
   FLUSH PRIVILEGES;
4. Change application properties according to the data in the step 3. Set free port for the server.
5. Install Postman (if not installed). Create a new collection. Create 5 new requests:
- GET to get all the todos
- GET to get a specific todo
- POST to add a new todo
- PUT to be able to update a specific todo
- DELETE to delete a specific todo
6. Run the TodoApplication.
7. Open Postman and send different requests.
8. Stop the TodoApplication.
9. [Optional] Check the correctness of the program either by visiting the page of the server or
   exploring the created database.

> **Feedback**
> - Was it easy to complete the task using AI?
    > Yes, it was easy to complete this task using AI.
> - How long did task take you to complete? (Please be honest, we need it to gather anonymized statistics)
    > About an hour and a half.
> - Was the code ready to run after generation? What did you have to change to make it usable?
    > No, it was not ready to run after generation. Some pieces of code were incomplete. I had either to ask AI to provide me with more detailed code or adjust
    > the code in the IntelliJ using its hints.
> - Which challenges did you face during completion of the task?
    > Generally, there were not so many challenges I faced during completion of the task. One confusing challenge was to determine dependencies with their versions
    > and configuration properties.
> - Which specific prompts you learned as a good practice to complete the task?
    > A good practice to complete the task is to provide a detailed prompt with the task description, working environment and required tools to complete the task.
