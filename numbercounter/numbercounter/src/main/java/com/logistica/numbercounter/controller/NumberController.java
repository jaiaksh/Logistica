package com.logistica.numbercounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.numbercounter.service.NumberCounterService;

@RestController
public class NumberController {

	@Autowired
	private NumberCounterService numberCounterService; 
	
	@GetMapping("/api/incrementNumber")
	@ResponseBody
	public ResponseEntity<String> incrementCounter() throws Exception 
	{ 
		try {
		return new ResponseEntity<String>("Final count is: "+numberCounterService.incrementCounter(),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>("Final count could not be received: "+numberCounterService.incrementCounter(),HttpStatus.BAD_REQUEST);
		}
	}

}
