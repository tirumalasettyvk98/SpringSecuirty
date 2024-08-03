package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        log.info(" <--- Before AuthenticationManagerBuilder Method  --->" +this.getClass().getName().toString());
        auth.userDetailsService(userDetailsService);
        log.info(" <---After AuthenticationManagerBuilder Method  --->" +this.getClass().getName().toString());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("<--- Before HttpSecurity Method ---> " +this.getClass().getName());

        http.authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/user").hasAnyRole("ADMIN,USER")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .and()
                .formLogin();

        log.info(" <--- After HttpSecurity Method ----> " +this.getClass().getName());

    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

}
