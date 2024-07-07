// run from root with: mvn spring-boot:run

package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * The first annotation on our MyApplication class is @RestController. 
 * This is known as a stereotype annotation. 
 * It provides hints for people reading the code and for Spring that the class 
 * plays a specific role. In this case, our class is a web @Controller, 
 * so Spring considers it when handling incoming web requests.
 */
@RestController
/*
 * The second class-level annotation is @SpringBootApplication.
 * This annotation is known as a meta-annotation, it combines
 * 
 * @SpringBootConfiguration, @EnableAutoConfiguration and @ComponentScan.
 * Of those, the annotation we’re most interested in here is
 * 
 * @EnableAutoConfiguration. @EnableAutoConfiguration tells Spring Boot to
 * “guess” how you want to configure Spring, based on the jar dependencies
 * that you have added. Since spring-boot-starter-web added Tomcat and
 * Spring MVC, the auto-configuration assumes that you are developing a web
 * application and sets up Spring accordingly.
 */
@SpringBootApplication
public class MyApplication {

    /*
     * The @RequestMapping annotation provides “routing” information.
     * It tells Spring that any HTTP request with the / path should be mapped to
     * the home method. The @RestController annotation tells Spring to render the
     * resulting string directly back to the caller.
     */
    @RequestMapping("/")
    String home() {
        return "Server is on!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @PostMapping("/data")
    public MyData postData(@RequestBody MyData data) {
        // Process the data as needed
        // For now, just return it
        return data;
    }

}