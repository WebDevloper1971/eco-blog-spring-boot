package com.minimal.blog_api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    private final String origin;
    public CorsConfig(@Value("${custom.allowedOrigin}")String origin){
        this.origin = origin;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        System.out.println("-------------------------------------------------------->        " + origin);
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins(origin);
            }
        };
    }

}
