/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.EventType;

/**
 * @author iddymagohe
 *
 */
public interface EventTypeRepository extends MongoRepository<EventType, String>{

}
