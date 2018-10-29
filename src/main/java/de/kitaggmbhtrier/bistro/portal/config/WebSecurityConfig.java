package de.kitaggmbhtrier.bistro.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import de.kitaggmbhtrier.bistro.portal.controller.MealCheckController;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	String rememberMeKey = null;
	int rememberMeTokenValiditySeconds = 604800; // Default to one week

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
			.antMatchers("/admin", "/h2-console/**").authenticated()
			.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl(MealCheckController.URL_MEAL_CHECK).permitAll();
//			.and().rememberMe().rememberMeServices(rememberMeService(rememberMeKey)).key(rememberMeKey).tokenValiditySeconds(rememberMeTokenValiditySeconds);
		
//		http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy);
		
		super.configure(http);
	}
	
//	public RememberMeServices rememberMeService(String internalSecretKey) {
//		
//	}
}
