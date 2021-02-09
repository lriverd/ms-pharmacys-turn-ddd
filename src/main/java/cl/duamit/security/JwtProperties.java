package cl.duamit.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 04-08-2020
 * @since 1.0.0 - 04-08-2020
 */
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    protected static final String TOKEN_PREFIX = "Bearer ";

    private String secret;
    private long timeExp;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getTimeExp() {
        return timeExp;
    }

    public void setTimeExp(long timeExp) {
        this.timeExp = timeExp;
    }
}
