/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.City;
import com.idrene.emefana.domain.Provider;


/**
 * @author iddymagohe
 *
 */
public interface ProviderRepository extends MongoRepository<Provider,String>{
	
	Page<Provider> findByCityOrderByNameASC(City city, Pageable pegiable);
	
	Page<Provider> findByCityOrderByScoreDesc(City city,TextCriteria criteria, Pageable pageable);
}
