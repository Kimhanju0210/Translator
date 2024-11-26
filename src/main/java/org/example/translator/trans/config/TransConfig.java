package org.example.translator.trans.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransConfig {

    @Bean
    public TransConfig transConfig() {
        return new TransConfig();
    }

}
