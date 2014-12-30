/**
 * 
 */
package com.idrene.emefana.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idrene.emefana.domain.Person;

/**
 * @author iddymagohe
 *
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByLastName(@Param("name") String name);
	List<Person> findByFirstNameLike(@Param("name") String name);

}