/**
 * 
 */
package com.idrene.emefana.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private AuthenticationTokenProcessingFilter authTokenProcessingFilter;
	
	@Autowired
    public void configureAuthBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement()
				   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				 .and()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				//.addFilterAfter(authTokenProcessingFilter)
				.addFilter(authTokenProcessingFilter)
			
//				.formLogin()
//				  .successHandler(authSuccess)
//				  .failureHandler(authFailure)
				.authorizeRequests()
				   .antMatchers("/resources/**", "/api/authenticate").permitAll()                 
		           //.antMatchers("/admin/**").hasRole("ADMIN")
		           //.antMatchers("/providers/**").hasRole("ADMIN")   
		           //.antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")  
		           //.anyRequest().authenticated();
				.antMatchers("/persons").authenticated();
				//.permitAll();
				     
	}

}
