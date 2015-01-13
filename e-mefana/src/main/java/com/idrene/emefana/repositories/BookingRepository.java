/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.idrene.emefana.domain.Booking;

/**
 * @author iddymagohe
 *
 */
public interface BookingRepository extends MongoRepository<Booking, String>,QueryDslPredicateExecutor<Booking>{

}
