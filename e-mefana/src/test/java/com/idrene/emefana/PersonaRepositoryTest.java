package com.idrene.emefana;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.idrene.emefana.domain.Person;
import com.idrene.emefana.repositories.PersonRepository;

public class PersonaRepositoryTest extends AbstractIntegrationTest{
	
	 @Autowired 
	 private PersonRepository repository;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMongoOps() {
		 repository.deleteAll();
		 assertTrue(0 == repository.count());
	     Person person = new Person("IDRENE","Iddy","Magohe");
	     person = repository.save(person);
	     
	     Person lastNameResults = repository.findByLastName("Magohe").get(0);
	     Person firstNameResults = repository.findByFirstNameLike("Id*").get(0);
	     assertNotNull(lastNameResults);
	     assertNotNull(firstNameResults);
	}

}
