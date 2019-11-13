package com.example.travel.config;

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

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                          DataSource dataSource) {
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

                .antMatchers("/checkList/getPaidChecklists").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/checkList/getNotPaidChecklists").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/checkList/toPay").access("hasAnyRole('USER', 'ADMIN')")

                .antMatchers("/comment/getComments").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/comment/putMark").access("hasAnyRole('USER', 'ADMIN')")

                .antMatchers("/payment/getConfirmPayments").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/payment/getUnConfirmPayments").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/payment/buyTour").access("hasAnyRole('USER', 'ADMIN')")

                .antMatchers("/tour/addComment").access("hasAnyRole('USER', 'ADMIN')")

                .antMatchers("/user/me").access("hasAnyRole('USER', 'ADMIN')")

                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}
