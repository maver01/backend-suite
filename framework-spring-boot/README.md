# Spring Boot framework

Create Maven project using `README.md` in framework-java-SE.

## Project structure:

The following listing shows a typical layout:

```
com
 +- example
     +- myapplication
         +- MyApplication.java
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
```

The `MyApplication.java` file would declare the main method, along with the basic @SpringBootApplication, as follows:

```
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
```

To add springboot dependencies without adding each version, you can add springboot parent to the `pom.xml`, with the most recent springboot version available in the main springboot website:
```
  <!-- Add Spring Boot Parent -->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>3.3.3</version> <!-- Use the appropriate Spring Boot version -->
      <relativePath/> <!-- Lookup parent from repository -->
  </parent>
```
