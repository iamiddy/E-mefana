/**
 * 
 */
package com.idrene.emefana.rest.resources;

import lombok.Getter;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class TokenResource {

	@Getter public final String token;

	public TokenResource(String token) {
		this.token = token;
	}
	
	
}
