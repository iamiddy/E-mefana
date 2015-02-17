/**
 * 
 */
package com.idrene.emefana.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idrene.emefana.domain.MetaResource;
import com.idrene.emefana.service.MetaService;

/**
 * @author iddymagohe
 * @since 1.0
 */
@Controller
public class MetaResourceController {
	
	@Autowired
	private MetaService metaService;
	
	@RequestMapping(value={"api/metadata","app/providers/api/metadata"}, method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<MetaResource<?>>> retrieveMetadata(){
		return new ResponseEntity<>(metaService.retrieveMeta(),HttpStatus.OK);
		
	}

}
