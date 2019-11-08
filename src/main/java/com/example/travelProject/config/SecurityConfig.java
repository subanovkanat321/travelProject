package com.example.travelProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/user/getAll").access("hasRole('ADMIN')")
                .antMatchers("/user/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/user/add").access("hasRole('ADMIN')")
                .antMatchers("/user/update").access("hasRole('ADMIN')")
                .antMatchers("/user/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/user/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/buyTour").access("hasRole('USER')")
                .antMatchers("/toPay").access("hasRole('USER')")
                .antMatchers("/registration").permitAll()
                .antMatchers("/addComment").access("hasRole('USER')")
                .antMatchers("/putMark").access("hasRole('USER')")

//                .antMatchers("/tour/getAll").access("hasRole('ADMIN')")
                .antMatchers("/tour/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/tour/add").access("hasRole('ADMIN')")
                .antMatchers("/tour/update").access("hasRole('ADMIN')")
                .antMatchers("/tour/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/tour/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/payment/getAll").access("hasRole('ADMIN')")
                .antMatchers("/payment/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/payment/add").access("hasRole('ADMIN')")
                .antMatchers("/payment/update").access("hasRole('ADMIN')")
                .antMatchers("/payment/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/payment/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/kindness/getAll").access("hasRole('ADMIN')")
                .antMatchers("/kindness/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/kindness/add").access("hasRole('ADMIN')")
                .antMatchers("/kindness/update").access("hasRole('ADMIN')")
                .antMatchers("/kindness/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/kindness/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/feedback/getAll").access("hasRole('ADMIN')")
                .antMatchers("/feedback/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/feedback/add").access("hasRole('ADMIN')")
                .antMatchers("/feedback/update").access("hasRole('ADMIN')")
                .antMatchers("/feedback/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/feedback/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/checkList/getAll").permitAll()
                .antMatchers("/checkList/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/checkList/add").access("hasRole('ADMIN')")
                .antMatchers("/checkList/update").access("hasRole('ADMIN')")
                .antMatchers("/checkList/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/checkList/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/category/getAll").access("hasRole('ADMIN')")
                .antMatchers("/category/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/category/add").access("hasRole('ADMIN')")
                .antMatchers("/category/update").access("hasRole('ADMIN')")
                .antMatchers("/category/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/category/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/categoryService/getAll").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/add").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/update").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/price/getAll").access("hasRole('ADMIN')")
                .antMatchers("/price/getById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/price/add").access("hasRole('ADMIN')")
                .antMatchers("/price/update").access("hasRole('ADMIN')")
                .antMatchers("/price/deleteById/{id}").access("hasRole('ADMIN')")
                .antMatchers("/price/deleteAll").access("hasRole('ADMIN')")

                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}
