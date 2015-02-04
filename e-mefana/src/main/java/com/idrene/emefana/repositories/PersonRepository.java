/**
 * 
 */
package com.idrene.emefana.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idrene.emefana.domain.User;

/**
 * @author iddymagohe
 *
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<User, String> ,QueryDslPredicateExecutor<User>{
	List<User> findByLastName(@Param("name") String name);
	List<User> findByFirstNameLike(@Param("name") String name);
	User findByIdOrEmailAddressAllIgnoreCase(String userId, String email);
	User findByEmailAddressAllIgnoreCase(String email);

}