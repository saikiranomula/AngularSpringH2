package com.alumon.login;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class StartApp {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

// run this only on profile 'demo', avoid run this in test
//    @Profile("demo")
//    @Bean
//    CommandLineRunner initDatabase(UserRepo repository) {
//    	Date date = new Date();
//        return args -> {
//            repository.save(new User(1L,"snomula1", "password","pass",5712327952L,date));
//            repository.save(new User(2L,"Aasdfdd", "snomula","adsf",000L,date));
//            repository.save(new User(3L,"Aasdfadf", "snomula","asdf",000L,date));
//            repository.save(new User(4L,"Aasdf", "snomula","dfdfd",000L,date));
//            repository.save(new User(5L,"Adfdsf", "snomula","ewe##",000L,date));
//            repository.save(new User(6L,"Akmkmnj", "snomula","###",000L,date));
//            repository.findAll().forEach(System.out::println);
//        };
//    }
    
    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
