package com.org.ecommerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.jwt")
public class Config {
    private String secret;
    private int expiration;
    
    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
