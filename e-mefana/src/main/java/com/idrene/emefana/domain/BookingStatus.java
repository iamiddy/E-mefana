/**
 * 
 */
package com.idrene.emefana.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 *
 */
public class BookingStatus {
	
	@Getter @Setter private Price amountDue;
	@Getter @Setter private Price amountPaid;
	@Getter @Setter BOOKINGSTATE currentState; 
	
	public enum BOOKINGSTATE {
		NEW, CONFIRMED, FULFILLMENT, DONE, CANCELLED
	}

	/**
	 * @param amountDue
	 * @param amountPaid
	 * @param currentState
	 */
	@PersistenceConstructor
	public BookingStatus(Price amountDue, Price amountPaid,BOOKINGSTATE currentState) {
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.currentState = currentState;
	}
	
	
}
