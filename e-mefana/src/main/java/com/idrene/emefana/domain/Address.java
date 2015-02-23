/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author iddymagohe
 *
 */
public class Address {
	
	@Getter @Setter private String street;
	
	@Getter @Setter private String streetLine2;
	@DBRef
	@Getter @Setter private City city;

	/**
	 * @param street
	 * @param city
	 */
	@PersistenceConstructor
	public Address(String street, City city) {
		this.street = street;
		this.city = city;
	}
	
	
}
