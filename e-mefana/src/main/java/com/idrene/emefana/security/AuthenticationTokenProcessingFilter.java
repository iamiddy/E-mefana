/**
 * 
 */
package com.idrene.emefana.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Component
public class AuthenticationTokenProcessingFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private  UtilityBean utilityBean;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#setAuthenticationManager(org.springframework.security.authentication.AuthenticationManager)
	 */
	@Autowired
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#setAuthenticationSuccessHandler(org.springframework.security.web.authentication.AuthenticationSuccessHandler)
	 */
	@Autowired
	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		super.setAuthenticationSuccessHandler(successHandler);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#setAuthenticationFailureHandler(org.springframework.security.web.authentication.AuthenticationFailureHandler)
	 */
	@Override
	@Autowired
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);

		String authToken = this.extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);

		if (userName != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (TokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
	
	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return utilityBean.decodePropertyValue(authToken);
	}

}
