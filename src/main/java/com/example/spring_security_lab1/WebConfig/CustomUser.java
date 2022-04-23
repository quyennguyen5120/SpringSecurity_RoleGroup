package com.example.spring_security_lab1.WebConfig;

import com.example.spring_security_lab1.Entity.Role;
import com.example.spring_security_lab1.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUser implements UserDetails {

    UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>(userEntity.getRoless().size());
        for(Role role : userEntity.getRoless()){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        if(userEntity != null)
            return userEntity.getPassword();
        else
            return "";
    }

    @Override
    public String getUsername() {
        if(userEntity != null)
            return userEntity.getUsername();
        else
            return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
