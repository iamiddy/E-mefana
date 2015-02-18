/**
 * 
 */
package com.idrene.emefana.rest.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 1.0
 */
public class ResponseStatus {
	
	@Getter @Setter int  statusCode;
	@Getter @Setter private String statusPhrase;
	@Getter @Setter private List<String> messages = new ArrayList<>();
	
	public ResponseStatus(int statusCode, String statusPhrase) {
		this.statusCode = statusCode;
		this.statusPhrase = statusPhrase;
	}
	
	public void addMessage(String message){
		messages.add(message);
	}
	
	
}
