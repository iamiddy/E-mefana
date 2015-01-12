/**
 * 
 */
package com.idrene.emefana.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author iddymagohe
 *
 */
@Document(collection="bookings")
@TypeAlias("booking")
public class Booking {
	
	@Id
	@Getter @Setter private String bid;
	
	@DBRef
	@Getter @Setter Person customer;
	
	@DBRef
	@Getter @Setter private Provider provider;
	
	@DBRef
	@Getter @Setter EventType event;
	
	@Indexed
	@Getter @Setter Date startDate;
	
	@Indexed
	@Getter @Setter Date endDate;
	
	@Getter @Setter BookingStatus status;
	
	@Getter @Setter private List<BookingService> services = new LinkedList<>();
	
	
}
