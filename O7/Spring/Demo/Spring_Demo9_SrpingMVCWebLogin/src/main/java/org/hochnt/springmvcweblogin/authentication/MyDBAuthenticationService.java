package org.hochnt.springmvcweblogin.authentication;

import java.util.ArrayList;
import java.util.List;

import org.hochnt.springmvcweblogin.dao.UserInfoDAO;
import org.hochnt.springmvcweblogin.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private UserInfoDAO userInfoDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDAO.findUserInfo(username);
        System.out.println("UserInfo= " + userInfo);
 
        if (userInfo == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
         
        // [USER,ADMIN,..]
        List<String> roles= userInfoDAO.getUserRoles(username);
         
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(roles!= null)  {
            for(String role: roles)  {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }        
       /*
        * In spring-security-core:5.0.0.RC1, the default PasswordEncoder is built as a 
        * DelegatingPasswordEncoder. When you store the users in memory, 
        * you are providing the passwords in plain text and when trying to retrieve the encoder from the DelegatingPasswordEncoder 
        * to validate the password it can't find one that matches the way in which these passwords were stored.
        * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
        * https://stackoverflow.com/questions/46999940/spring-boot-passwordencoder-error
        * */  
        UserDetails userDetails = User.withDefaultPasswordEncoder().username(userInfo.getUserName()).
        		password(userInfo.getPassword()).roles(roles.toArray(new String[0])).build();
//        UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
//                userInfo.getPassword(),grantList);
 
        return userDetails;
    }
     
}
