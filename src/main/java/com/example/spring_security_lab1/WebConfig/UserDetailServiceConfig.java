package com.example.spring_security_lab1.WebConfig;

import com.example.spring_security_lab1.Entity.UserEntity;
import com.example.spring_security_lab1.Repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
