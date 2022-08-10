package com.bbenslimane.app.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {

//        registry.addMapping("/**")    //allow all routes(controllers)
//                .allowedMethods("*")  //allow all methods
//                .allowedOrigins("*"); /allow origins

        registry.addMapping("/users")
                .allowedMethods("GET", "POST", "PUT")
                .allowedOrigins("http://localhost:4200");
    }
}
