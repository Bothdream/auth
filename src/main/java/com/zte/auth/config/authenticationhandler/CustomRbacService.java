package com.zte.auth.config.authenticationhandler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义权限过判断服务
 */
@Component("customRbacService")
public class CustomRbacService{

     public boolean hasPermission(HttpServletRequest request, Authentication authentication){
         Object principal = authentication.getPrincipal();
         if (principal instanceof UserDetails) {
           UserDetails userDetails =  (UserDetails) principal;
           String uri = request.getRequestURI();
           SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(uri);
           return userDetails.getAuthorities().contains(simpleGrantedAuthority);
         }
         return false;
     }
}
