package com.unalasil.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.unalasil.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.inMemoryAuthentication()
				.withUser("unal")
				.password(encodePWD().encode("12345"))
				.roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
//		  http.authorizeRequests()
//          .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//          .antMatchers("/admin/**").hasAuthority("ADMIN")
//          .anyRequest().authenticated()
//          .and()
//          .formLogin().permitAll()
//          .and()
//          .logout().permitAll()
//          .and()
//          .exceptionHandling().accessDeniedPage("/403")
//          ;
		
        http
        	.httpBasic()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/admin/**").hasRole("admin")
	        .antMatchers("/users/**").hasRole("user")
	        .and()
	        .csrf().disable()
	        .formLogin().disable();
	    }
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
}
