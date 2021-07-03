package com.petsuite;

import com.petsuite.security.JwtSuccessHandler;
import com.petsuite.security.JwtAuthenticationEntryPoint;
import com.petsuite.security.JwtAuthenticationProvider;
import com.petsuite.security.JwtAuthenticationTokenFilter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }
    

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

          http.csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/api/clients/load").permitAll()
                .antMatchers("/api/dog_walkers/load").permitAll()
                .antMatchers("/api/dog_day_cares/load").permitAll()
                .antMatchers("/api/clients/**").access("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/dogdaycareservices/**").access("hasRole('ROLE_DOGDAYCARE') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/dog_walkers/**").access("hasRole('ROLE_DOGWALKER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/dog_day_cares/**").access("hasRole('ROLE_DOGDAYCARE') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/dogs/register").access("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/dogs/findmydog").access("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/walkpetitions/**").access("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DOGWALKER')")
                .antMatchers("/api/walkinvoices/**").access("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DOGWALKER')")
                .antMatchers("/api/dog_day_care_invoices/**").access("hasRole('ROLE_DOGDAYCARE') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
                .antMatchers("/api/clients/notifications").permitAll()       
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
     @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
