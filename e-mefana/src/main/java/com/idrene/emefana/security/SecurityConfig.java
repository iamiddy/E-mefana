/**
 * 
 */
package com.idrene.emefana.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private AuthFailure authFailure;

    @Autowired
    private AuthSuccess authSuccess;
    
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    public void configureAuthBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.exceptionHandling()
				  .authenticationEntryPoint(unauthorizedHandler)
				  .and().
				formLogin()
				  .successHandler(authSuccess)
				  .failureHandler(authFailure)
				.and()
				.authorizeRequests()
				   //.antMatchers("/resources/**", "/signup", "/about").permitAll()                  
		           //.antMatchers("/admin/**").hasRole("ADMIN")
		           //.antMatchers("/providers/**").hasRole("ADMIN")   
		           //.antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")  
		           //.anyRequest().authenticated();
				.antMatchers("/**")
				.permitAll();
				     
	}

}
