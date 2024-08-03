package com.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="Auth-Service",  url= "http://localhost:9898")
public interface AuthClient {

    @PostMapping("/validate")
     ResponseEntity<?> validate (@RequestBody String token );
}
