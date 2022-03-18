# Mobiquity - QA Coding Challenge

This is a test project prepared for the coding challenge assignment for the “senior QA engineer” position at Mobiquity. 

This includes the solution for the task given by Mobiquity which is a very simple API test case.

### 1.	Built With

Following frameworks/libraries has used to build this project
- Java Spring framework
- Java Kotlin library
- REST Assured library
- Maven Java tool

### 2.	Prerequisites

Following things need to be installed for this automation suite to run.

  - Java 1.8 JDK or any later version of Java
  - Apache Maven 3.8.5

### 3.	Installation

Follow the below steps to run the tests locally.

1.	Clone the [git repo](https://github.com/ChamalikaH/mobiquity-qaTest.git)
2.	Import the project to Eclipse IDE/ IntelliJ IDEA or any other editor
3.	Run the tests with this command on the terminal. There will be a detailed log on the terminal with test data and results.

```sh
mvn "-Dspring.profiles.active=qa" test
```
4. If there are multiple environments to run other than QA, another property file can be added under the 'resource' folder


