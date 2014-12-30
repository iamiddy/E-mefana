package com.idrene.emefana.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.swagger.annotations.Api;

@Controller
@Api(value="Hello", description="Operations on Businesses", position = 2)
public class HelloWorldController {
	@RequestMapping("/hello")
	public String hello(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "index";
	}

}
