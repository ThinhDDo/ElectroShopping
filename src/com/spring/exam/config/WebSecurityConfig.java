package com.spring.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.exam.sys.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = ("com.spring.exam"))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	/**
	 * Security Configuration
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Grant access to specific url (Mapping path ->> role)
		http.authorizeRequests()
			.antMatchers("/login", "/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
			.antMatchers("/profile", "/checkout/**","/cart/**", "/history/**", "/details/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/store/**", "/search/**", "/product/**", "/register").permitAll()
			.antMatchers("/admin/**").permitAll()
		.and()
			.formLogin()
			.loginPage("/login") // Login Controller
			.loginProcessingUrl("/login-form") // Form action
			.usernameParameter("username") // Form field
			.passwordParameter("password") // Form field
			.defaultSuccessUrl("/") // request controller if success
			.failureUrl("/login?error=true") // request controller if fail (with param)
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
		.and()
			.csrf()
			.disable();
	}
}
