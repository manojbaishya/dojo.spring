# Getting Started

### Developer Guide

* Swagger URL: `{{protocol}}://{{hostname}}:{{port}}/swagger-ui/index.html`
* OpenAPI JSON Docs: `{{protocol}}://{{hostname}}:{{port}}/v3/api-docs`

### Backlog

1. Update Bruno API Documentation from SpringDoc OpenAPI JSON files.
   * Migrate the URI paths with `/api` prefix
2. Implement integration with a third party API service using Java's HttpClient
3. Test Spring Boot Virtual Threads throughput using a database connection
4. Migrate the ASP.NET Core Web API project — https://github.com/manojbaishya/dojo.api/
5. Implement Unit and Integration tests for new models in org.dojo.spring.department.billing.Transaction, org.dojo.spring.todo,  .
6. Implement performance tests in k6 for the existing models — Department and Transaction.
7. Implement JWT OAuth 2.0 Authentication and Authorization with RBAC — both Token and Resource Servers in this project
8. Implement Checker Framework instrumentation on the code — Follow PR https://github.com/typetools/checker-framework/pull/7055

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.5/maven-plugin/build-image.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/3.4.5/reference/actuator/index.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.5/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/3.4.5/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### System Design Problems

#### Basics

1. Design a simple URL shortening service.
2. Design a basic chat application.
3. Design a file storage system.
4. Design a simple social media platform.
5. Design a simple search engine.
6. Design a simple e-commerce website.
7. Design a basic ride-sharing system.
8. Design a basic video streaming service.
9. Design a simple recommendation system.
10. Design a basic food delivery app.
11. Design a parking lot management system.
12. Design a simple music streaming service.
13. Design a basic online ticket booking system.
14. Design a simple note-taking application.
15. Design a weather forecasting system.
16. Design a basic email service.
17. Design a file synchronization system.
18. Design a simple calendar application.
19. Design a basic online quiz platform.
20. Design a user authentication system.

#### Advanced

1. Design a URL-shortening service like bit.ly.
2. Design a distributed key-value store like Redis.
3. Design a scalable social network like Facebook.
4. Design a scalable recommendation system like Netflix.
5. Design a distributed file system like Hadoop's HDFS.
6. Design a real-time messaging system like WhatsApp.
7. Design a web crawler like Google.
8. Design a distributed cache like Memcached.
9. Design a content delivery network (CDN) like Cloudflare.
10. Design a scalable search engine like Google.
11. Design a ride-sharing system like Uber.
12. Design a video streaming service like YouTube.
13. Design an online food delivery system like Zomato.
14. Design a collaborative document editing system like Google Docs.
15. Design an e-commerce platform like Amazon.
16. Design a recommendation system for an online marketplace.
17. Design a fault-tolerant distributed database system.
18. Design a scalable event-driven system like Twitter.
19. Design a scalable photo-sharing platform like Instagram.
20. Design a distributed task scheduling system.

### Design Patterns

#### Creational patterns
These patterns provide various object creation mechanisms, which increase the flexibility and reuse of existing code.

1. Factory Method
2. Abstract Factory
3. Builder
4. Prototype
5. Singleton


#### Structural patterns
These patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient.

1. Adapter
2. Bridge
3. Composite
4. Decorator
5. Facade
6. Flyweight
7. Proxy

#### Behavioral patterns
These patterns are concerned with algorithms and the assignment of responsibilities between objects.

1. Chain of Responsibility
2. Command
3. Iterator
4. Mediator
5. Memento
6. Observer
7. State
8. Strategy
9. Template Method
10. Visitor
