package com.demo.service;

import com.demo.model.MyUserDetails;
import com.demo.model.User;
import com.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {

   @Autowired
   private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("Before   ---> "+this.getClass().getName());

        log.info("UserName  ---> "+ username+"  class --> "+this.getClass().getName());
        User user=userRepo.findByUsername(username);

        log.info("End of   ---> "+this.getClass().getName());
        return new  MyUserDetails(user);
    }
}
