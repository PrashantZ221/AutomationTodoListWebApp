
**Introduction**

This repository contains a Selenium automation framework implemented using the Page Object Model (POM) design pattern in Java. It contains two test files AddEditTodoTests and TodoListTests.

**Features**

- Implementation of Page Object Model (POM) for better code organization and maintenance.
- Integration with Allure report for enhanced test reporting and visualization.
- Method to capture screenshots on test failure.
- Integration with TestNG for test execution. 
- Management of project dependencies and build process using Maven

**Setup**

- Clone the repository to your local machine. (Windows Machine)
- Install Java, Maven and set their environment variables
- Navigate to project folder. Run 'mvn clean' and then 'mvn test' command to run all the test cases
- You can view the screenshot of failed test cases in screenshots folder
- The result are saved in target/allure-results folder after execution. To view the result run 'allure serve <path till allure-reports>' command in terminal. Eg: allure serve "C:\Users\Prashant.Agarwal\Desktop\AutomationTodoListWebApp\target\allure-results"

If you have any questions or suggestions, please contact Prashant Agarwal (prash221@gmail.com)
