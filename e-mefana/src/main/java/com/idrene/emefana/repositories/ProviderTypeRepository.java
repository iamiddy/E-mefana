/**
 * 
 */
package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.ProviderType;

/**
 * @author iddymagohe
 *
 */
public interface ProviderTypeRepository extends MongoRepository<ProviderType, String> {
	
}
