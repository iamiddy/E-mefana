/**
 * 
 */
package com.idrene.emefana.rest.controllers.tokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idrene.emefana.rest.resources.EmefanaUser;
import com.idrene.emefana.rest.resources.TokenResource;
import com.idrene.emefana.security.TokenUtils;
import com.idrene.emefana.util.UtilityBean;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Controller
@RequestMapping(value="/api")
public class TokenController {
	
	
	@Autowired
	@Lazy
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  UtilityBean utilityBean;
	
	@Autowired
	private  UserDetailsService userDetailsService;
	

	@RequestMapping(value="/authenticate", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> generateToken(@RequestBody EmefanaUser user){
		ResponseEntity<?> response = null;
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(),user.getCredential());
		
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			/*
			 * Reload user as password of authentication principal will be null
			 * after authorization and password is needed for token generation
			 */
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserId());
			String token = TokenUtils.createToken(userDetails);
			response = ResponseEntity.ok(new TokenResource(utilityBean.encodePropertyValue(token)));
		} catch (AuthenticationException e) {
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return response;
	} 

}
