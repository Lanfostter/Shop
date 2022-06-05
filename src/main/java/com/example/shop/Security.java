package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class Security extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

	// giải mã hóa password
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// phân quyền, xử lý login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**")
				// .hasAnyRole("ADMIN","SUBADMIN"
				.hasAnyAuthority("ROLE_ADMIN")
				// has authenticated
				.antMatchers("/cart/**", "/cartitem/**").authenticated()
				// everyone can join
				.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/css/**", "/img/**", "/home",
						"/api/**", "/msg/**")
				.permitAll().anyRequest().permitAll().and().csrf().disable().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").failureUrl("/login?err=true").defaultSuccessUrl("/home", true).and()
				.exceptionHandling().accessDeniedPage("/login")
				.and().logout().logoutSuccessUrl("/home");
	}
}
