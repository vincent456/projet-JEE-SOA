package com.example.REST.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping(method=RequestMethod.POST,value="/post")
	String postMessage(@RequestParam(name="message",required=true) String message) {
		return message;
	}
}
