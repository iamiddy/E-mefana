package com.idrene.emefana.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.idrene.emefana.domain.ServiceOffering;

public interface ServiceOfferingRepository extends MongoRepository<ServiceOffering, String>{
	
	//@Query('{"providerTypes":{"$ref":"provider_type","$id":"Venues"}}')
	List<ServiceOffering> findByProviderTypesTypeIn(List<String> type);

}
