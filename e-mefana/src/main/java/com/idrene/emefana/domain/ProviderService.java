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
public class ProviderService {

  @DBRef	
  @Getter @Setter private ServiceOffering service;
  
  @Getter @Setter private String description;
  
  @Getter @Setter private Price price;

/**
 * @param service
 * @param description
 * @param price
 */
 @PersistenceConstructor 
public ProviderService(ServiceOffering service, String description, Price price) {
	this.service = service;
	this.description = description;
	this.price = price;
}

public ProviderService(ServiceOffering service, String description) {
	this.service = service;
	this.description = description;
}
  
  
}
