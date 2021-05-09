package com.zte.auth.config.authenticationhandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        if ("user".equals(username)) {
            customUserDetails.setUserName("user");
            customUserDetails.setPassword(new BCryptPasswordEncoder().encode("123456"));
            customUserDetails.setEnabled(true);
            customUserDetails.setAccountNonExpired(true);
            customUserDetails.setCredentialsNonExpired(true);
            customUserDetails.setAccountNonLocked(true);
            List<String> roles = new ArrayList<>();
            roles.add("/");
            roles.add("/biz1");
            roles.add("/biz2");
            List<GrantedAuthority> re = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roles,","));
            customUserDetails.setAuthorities(re);
            return customUserDetails;
        }
        if ("admin".equals(username)) {
            customUserDetails.setUserName("admin");
            customUserDetails.setPassword(new BCryptPasswordEncoder().encode("123456"));
            customUserDetails.setEnabled(true);
            customUserDetails.setAccountNonExpired(true);
            customUserDetails.setCredentialsNonExpired(true);
            customUserDetails.setAccountNonLocked(true);
            List<String> roles = new ArrayList<>();
            roles.add("/");
            roles.add("/syslog");
            roles.add("/sysuser");
            List<GrantedAuthority> re = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(roles,","));
            customUserDetails.setAuthorities(re);
            return customUserDetails;
        }

        //1.查询用户信息

        //2.查询角色信息

        //3.查询权限信息
        return null;
    }
}
