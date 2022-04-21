package com.example.spring_security_lab1.WebConfig;

import com.example.spring_security_lab1.Entity.UserEntity;
import com.example.spring_security_lab1.Repository.CustomUser;
import com.example.spring_security_lab1.Repository.UserRepositoty;
import netscape.security.Privilege;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailServiceConfig implements UserDetailsService {

    @Autowired
    UserRepositoty userRepositoty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositoty.findByUsername(username);
        return new CustomUser(user);
    }


}
