/**
 * 
 */
package com.idrene.emefana.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idrene.emefana.rest.resources.ListingResource;
import com.idrene.emefana.rest.resources.ResponseStatus;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Controller
public class ListingResourceController {
	
	@RequestMapping(value={"api/provider","app/providers/api/provider"}, method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE , consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> registerListing(@RequestBody @Valid ListingResource listing,BindingResult result) throws URISyntaxException {
		ResponseEntity<ResponseStatus> response = null;
		if(result.hasErrors()){
			ResponseStatus body = new ResponseStatus(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
			result.getAllErrors().forEach(e -> body.addMessage(e.getDefaultMessage()));
			response = ResponseEntity.badRequest().body(body);
		}else{
			// TODO deal with listing
			response = ResponseEntity.created(new URI("http://api/provider")).body(new ResponseStatus(HttpStatus.CREATED.value(),HttpStatus.CREATED.getReasonPhrase()));
		}
		
		return response;
	}

}
