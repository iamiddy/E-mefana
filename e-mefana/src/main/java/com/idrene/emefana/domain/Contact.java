/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 *
 */
public class Contact {
	 @Getter @Setter private String type;
	 @Getter @Setter private String info;
	 @Getter @Setter private String description;
  
	 public enum ContactTypeEnum{
	  Telephone,
	  Mobile,
	  Email,
	  PoBox,
	  Website
  } 
}
