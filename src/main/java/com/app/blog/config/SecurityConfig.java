package com.app.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.blog.security.JwtAuthenticationEntryPoint;
import com.app.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtAuthenticationFilter jwtAuthenticatioFilter;

	public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticatioFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAuthenticatioFilter = jwtAuthenticatioFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Here within this security filter chain, method -> configure spring security
		// for which only enables basic authentication

		// Enabled HTTP basic authentication
		// Disable csrf
		// Authenticate all the requests
//		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults());

		// Authenticate access to some of the APIs and pass @EnableMethodSecurity to the
		// Security Config
//		http.csrf().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/api/**")
//				.permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

		// Allow /api/auth to all the users
//		http.csrf().disable()
//				.authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//						.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated());
		
		
		// JWT Authentication
		http.csrf().disable()
		.authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
		.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(jwtAuthenticatioFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build(); // Default Security

	}

	// Create Couple of users and store it in memory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails prince = User.builder().username("prince").password(passwordEncoder().encode("prince"))
//				.roles("USER").build();
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(prince, admin);
//	}

}
