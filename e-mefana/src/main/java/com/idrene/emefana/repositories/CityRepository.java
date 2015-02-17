/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.City;

/**
 * @author iddymagohe
 * @since 1.0
 *
 */
public interface CityRepository extends MongoRepository<City, String> {
	City findByCidIgnoreCase(String city);
}
