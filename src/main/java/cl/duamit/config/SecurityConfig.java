package cl.duamit.config;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/pharmacy/*").permitAll()
                .antMatchers("/pharmacy/*", "/location/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .anonymous().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("FormBased"));
    }

}
