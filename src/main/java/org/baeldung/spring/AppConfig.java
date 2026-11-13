package org.baeldung.spring;

import org.baeldung.security.ActiveUserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class AppConfig {
    // beans
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public ActiveUserStore activeUserStore() {
        return new ActiveUserStore();
    }

}