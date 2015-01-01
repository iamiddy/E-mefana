/**
 * 
 */
package com.idrene.emefana.domain;

import lombok.Getter;
import lombok.Setter;

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
}
