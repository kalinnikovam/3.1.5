package com.example.firstspringbootsecurityapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    private final ServletContext servletContext;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, ServletContext servletContext) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.servletContext = servletContext;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
        filter.setServletContext(servletContext);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/logout").authenticated()
                .antMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .csrf().disable()
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}