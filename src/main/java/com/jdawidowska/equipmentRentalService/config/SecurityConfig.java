package com.jdawidowska.equipmentRentalService.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import com.jdawidowska.equipmentRentalService.exception.UserNotFoundException;
import com.jdawidowska.equipmentRentalService.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), algorithm(), tokenLifetime());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();
        http.headers().frameOptions().disable(); // h2 console working
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/console/**").permitAll() // h2 console working
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/inventory").authenticated()
                .antMatchers("/api/inventory/add").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/inventory/remove/**").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/rentedInventory").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/rentedInventory/user/**").authenticated()
                .antMatchers("/api/renting/rent").authenticated()
                .antMatchers("/api/renting/return").authenticated()
                .antMatchers("/api/users").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/history/user/**").authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(algorithm()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService(userRepository));
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256("secret".getBytes()); //TODO move to app props
    }

    @Bean
    public int tokenLifetime() {
        return 10 * 60 * 1000; //TODO move to app props
    }
}
