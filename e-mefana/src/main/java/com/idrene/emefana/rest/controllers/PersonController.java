package com.idrene.emefana.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idrene.emefana.domain.User;
import com.idrene.emefana.repositories.PersonRepository;
import com.wordnik.swagger.annotations.Api;

@Controller
@Api(value="folks", description="Operations on Businesses", position = 2)
@PreAuthorize("hasRole('USER')")
class PersonController {

	@Autowired
	PersonRepository personRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	HttpEntity<PagedResources> persons(Pageable pageable,
			PagedResourcesAssembler assembler) {
		
		Page<User> persons = personRepository.findAll(pageable);
		
		return new ResponseEntity<>(assembler.toResource(persons),HttpStatus.OK);
	}
	

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	ResponseEntity<Resources<Resource<User>>> people(Pageable pageable,
			@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
		
		List<User> pp = new ArrayList<>();
		pp.add(new User("","Jane", "Doe"));
		pp.add(new User("","Billy Bob", "Thornton"));
		Resources<Resource<User>>folks=Resources.wrap(pp);
		return new ResponseEntity<>(folks,HttpStatus.OK);
	}
}
