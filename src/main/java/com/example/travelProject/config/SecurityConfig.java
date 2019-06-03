package com.example.travelProject.config;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

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

                .antMatchers("/user/getAll").permitAll()
                .antMatchers("/user/findById").access("hasRole('ADMIN')")
                .antMatchers("/user/add").access("hasRole('ADMIN')")
                .antMatchers("/user/update").access("hasRole('ADMIN')")
                .antMatchers("/user/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/user/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/tour/getAll").access("hasRole('ADMIN')")
                .antMatchers("/tour/findById").access("hasRole('ADMIN')")
                .antMatchers("/tour/add").access("hasRole('ADMIN')")
                .antMatchers("/tour/update").access("hasRole('ADMIN')")
                .antMatchers("/tour/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/tour/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/payment/getAll").access("hasRole('ADMIN')")
                .antMatchers("/payment/findById").access("hasRole('ADMIN')")
                .antMatchers("/payment/add").access("hasRole('ADMIN')")
                .antMatchers("/payment/update").access("hasRole('ADMIN')")
                .antMatchers("/payment/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/payment/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/kindness/getAll").access("hasRole('ADMIN')")
                .antMatchers("/kindness/findById").access("hasRole('ADMIN')")
                .antMatchers("/kindness/add").access("hasRole('ADMIN')")
                .antMatchers("/kindness/update").access("hasRole('ADMIN')")
                .antMatchers("/kindness/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/kindness/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/feedback/getAll").access("hasRole('ADMIN')")
                .antMatchers("/feedback/findById").access("hasRole('ADMIN')")
                .antMatchers("/feedback/add").access("hasRole('ADMIN')")
                .antMatchers("/feedback/update").access("hasRole('ADMIN')")
                .antMatchers("/feedback/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/feedback/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/checkList/getAll").permitAll()
                .antMatchers("/checkList/findById").access("hasRole('ADMIN')")
                .antMatchers("/checkList/add").access("hasRole('ADMIN')")
                .antMatchers("/checkList/update").access("hasRole('ADMIN')")
                .antMatchers("/checkList/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/checkList/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/category/getAll").access("hasRole('ADMIN')")
                .antMatchers("/category/findById").access("hasRole('ADMIN')")
                .antMatchers("/category/add").access("hasRole('ADMIN')")
                .antMatchers("/category/update").access("hasRole('ADMIN')")
                .antMatchers("/category/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/category/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/categoryService/getAll").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/findById").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/add").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/update").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/categoryService/deleteAll").access("hasRole('ADMIN')")

                .antMatchers("/price/getAll").access("hasRole('ADMIN')")
                .antMatchers("/price/findById").access("hasRole('ADMIN')")
                .antMatchers("/price/add").access("hasRole('ADMIN')")
                .antMatchers("/price/update").access("hasRole('ADMIN')")
                .antMatchers("/price/deleteById").access("hasRole('ADMIN')")
                .antMatchers("/price/deleteAll").access("hasRole('ADMIN')")

                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}
