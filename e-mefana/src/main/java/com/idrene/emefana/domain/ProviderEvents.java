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
public class ProviderEvents {
  @DBRef	
  @Getter @Setter private EventType event;
  
  @Getter @Setter private String description;
}
