package dev.sasikumar.productserviceproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    // If I want a restTemplate I will call this function
    // I'll get a *** new restTemplate special Object ***
    // @Bean for making the newly created object as special object and puts this into application context so that we can use this object throughout the project
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    /* annotations vs beans

    we can annotate a class to create a special object of its class
    But if the class is already present in a library, we can't annotate there,
    so we create a object from the library and
    make the created object as special by annotating as @Bean

     */
}
