/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.Booking;

/**
 * @author iddymagohe
 *
 */
public interface BookingRepository extends MongoRepository<Booking, String>{

}
