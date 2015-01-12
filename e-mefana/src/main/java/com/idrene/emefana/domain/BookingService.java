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
public class BookingService {
	@Getter @Setter private Price amountDue;
	@Getter @Setter private Price amountPaid;
	@Getter @Setter private ProviderService providerService;
	
}
