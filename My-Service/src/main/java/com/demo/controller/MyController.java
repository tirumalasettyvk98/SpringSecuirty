package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Autowired
    private AuthClient authClient;

    @GetMapping("/message")
    public String getMessage(@RequestHeader("Authorization") String token)
    {
       
    	String jwt= token.substring(7);
    	
    	ResponseEntity<?> result= authClient.validate(jwt);
         if(result.getStatusCode()== HttpStatus.OK)
        return "you are a validate user and Message is secret passed";

         else
             return "Not allowed to access due to authentication failed";
    }


}
