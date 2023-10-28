package com.greatlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.service.DomainUserDetailsService;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DomainUserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();

		httpSecurity.authorizeRequests().antMatchers("/login", "/h2-console/**", "/h2-console**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users**", "/api/users/**", "/api/employees/roles").permitAll()
				.antMatchers(HttpMethod.GET, "/api/employees**", "/api/employees/{id}", "/api/employees/search/{name}",
						"/api/employees/sort")
				.hasAnyRole("USER", "ADMIN").antMatchers(HttpMethod.POST, "/api/employees**", "/api/employees/**")
				.hasAnyRole("ADMIN").antMatchers(HttpMethod.PUT, "/api/employees/{id}").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/{id}").hasAnyRole("ADMIN").anyRequest().authenticated()
				.and().formLogin().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
