package de.kitaggmbhtrier.bistro.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();	// FIXME: For now disable CSRF until all forms and ajax calls would be updated ...
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/mealcheck/**").permitAll()
			.antMatchers("/login", "/", "/favicon.ico", "/webjars/**", "/css/**", "/img/**").permitAll()
			.antMatchers("/admin").authenticated();
		
//		http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy);
		
		super.configure(http);
	}
	
}
