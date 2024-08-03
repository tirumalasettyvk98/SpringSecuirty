package com.demo.filter;

import com.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader= request.getHeader("Authorization");

        String user=null;
        String jwt=null;

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer"))
        {
            jwt = authorizationHeader.substring(7);
            user=jwtUtil.extractUsername(jwt);
        }

        if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails= userDetailsService.loadUserByUsername(user);
            Boolean b= jwtUtil.validateToken(jwt,userDetails);

            if(b) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);

            }

            System.out.println("Hii---------------in secuirty");

        }

        System.out.println("Hii---------------OUTSIDE secuirty");
        filterChain.doFilter(request,response);
    }
}
