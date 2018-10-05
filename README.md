
![alt text](https://travis-ci.org/ayhanugurlu/postal-code-distance.svg?branch=master "Travis Status")

[![codecov.io](https://codecov.io/github/ayhanugurlu/postal-code-distance/coverage.svg?branch=master)](https://codecov.io/github/cainus/codecov.io?branch=master)

![BCH compliance](https://bettercodehub.com/edge/badge/ayhanugurlu/postal-code-distance?branch=master)

Http basic Authentication Username : admin Password : admin



REST service that returns the geographic (straight line) distance between two postal codes in the UK.

## Getting Started

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.au.ymor.PostalCodeDistanceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

- When the application is running, you can see endpoints from link below:

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

- Endpoints:

    [http://localhost:8080/getPostalCodeDistance](http://localhost:8080/api/v1/getPostalCodeDistance)

    [http://localhost:8080/updatePostalCodeLocation](http://localhost:8080/api/v1/updatePostalCodeLocation)


Project automatically import sample data from resources that defined in application.properties. 
(eg: import.file.name=postcode-outcodes.csv) 
 