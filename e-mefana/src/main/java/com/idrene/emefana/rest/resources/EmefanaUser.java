/**
 * 
 */
package com.idrene.emefana.rest.resources;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class EmefanaUser {
	
	@Getter @Setter private String userId;
	@Getter @Setter private String credential;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return userId;
	}
	
}
