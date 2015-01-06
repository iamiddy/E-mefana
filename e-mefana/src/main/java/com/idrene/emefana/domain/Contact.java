/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author iddymagohe
 *
 */
public class Contact {
	 @Getter @Setter private String type;
	 @Getter @Setter private String info;
	 @Getter @Setter private String description;
	 
	 
  
	 /**
	 * @param type
	 * @param info
	 * @param description
	 */
	@PersistenceConstructor
	public Contact(String type, String info, String description) {
		this.type = type;
		this.info = info;
		this.description = description;
	}



	public enum ContactTypeEnum{
	  Telephone,
	  Mobile,
	  Email,
	  PoBox,
	  Website
  } 
}
