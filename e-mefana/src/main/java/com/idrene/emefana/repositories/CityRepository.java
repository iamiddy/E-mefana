/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idrene.emefana.domain.City;

/**
 * @author iddymagohe
 *
 */

 @RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
 public interface CityRepository extends MongoRepository<City, String>{
 
	 
}
