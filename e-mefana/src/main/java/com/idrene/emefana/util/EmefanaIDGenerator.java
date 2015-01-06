/**
 * 
 */
package com.idrene.emefana.util;

import java.util.UUID;

/**
 * @author iddymagohe
 *
 */
public class EmefanaIDGenerator {
	
	public static String generateProviderId(){
		return UUID.randomUUID().toString();
	}
}
