# Assignment

---------

### This is a Gradle based Spring Boot project which requires JDK11

#### Steps to run the application on local:
1. Take a clone of the project in your local.
2. Run `gradle build` command to build the project.
3. Go to application.properties and in `spring.datasource.url` value replace <PATH_TO_DATABASE> with full path of accountsdb.accdb file present inside src/main/resources/static folder.
4. Go to AssignmentApplication.java and run the main method as spring boot app.
5. Access the dashboard by going to http://localhost:8080/dashboard

### Steps to run test cases on local:
1. Run `gradle test` command.

### Rest endpoints details are:
1. GET /ping
2. GET /accounts
3. POST /search

Sample request body for POST /search call:
```
{
  "accountId":1,
  "fromDate":null,
  "toDate":null,
  "fromAmount":"200",
  "toAmount":"800"
}
```
NOTE: Dates should be in `dd.MM.yyyy` format.

#### Basic auth is required while accessing these endpoints via Postman.

### Reports
1. Jacoco report is under `reports/jacoco` folder.
2. Junit reports is under `reports/tests` folder.
3. Sonar report was not able to generate as was using SonarQube community edition server, so have attached combination of screenshots in pdf format which is present inside `reports/sonar` folder.