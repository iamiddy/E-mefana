/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;

/**
 * @author iddymagohe
 *
 */
public enum EeventCategories {
	
	PRIVATE("private"),
	CORPORATE("corporate");
	
	@Getter private String name;
	
	EeventCategories(String name){
		this.name = name;
	}
}
