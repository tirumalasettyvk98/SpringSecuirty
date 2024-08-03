package com.demo.controller;

import com.demo.model.AuthenticationRequest;
import com.demo.model.AuthenticationResponse;
import com.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;



   @GetMapping("/hello")
    public String hello()
    {
        return "Welcome to Spring Security Demo";
    }

    @PostMapping("/auth")
    public ResponseEntity<?>  createAuthethicationToken(@RequestBody AuthenticationRequest authenticationRequest)
    {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword());

        authenticationManager.authenticate(token);

       UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

       String jwt= jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


//    @GetMapping("/user")
//    public String greetUser()
//    {
//        return "Hello User";
//    }
//
//    @GetMapping("/admin")
//    public String greetAdmin()
//    {
//        return "Hello Admin";
//    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate (@RequestBody String token )
    {
        UserDetails userDetails =userDetailsService.loadUserByUsername( jwtUtil.extractUsername(token));
       boolean result= jwtUtil.validateToken(token,userDetails);

       if(result)
       {
           return new ResponseEntity<>(true, HttpStatus.OK);

       }
       else
       {
           return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
       }

    }




}
