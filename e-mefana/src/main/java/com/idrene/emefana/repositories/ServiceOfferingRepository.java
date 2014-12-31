package com.idrene.emefana.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idrene.emefana.domain.ServiceOffering;

public interface ServiceOfferingRepository extends MongoRepository<ServiceOffering, String>{

}
