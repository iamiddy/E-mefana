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
public class BookingService {
	@Getter @Setter private Price amountDue;
	@Getter @Setter private Price amountPaid;
	@DBRef
	@Getter @Setter private ProviderService providerService;
	
}
