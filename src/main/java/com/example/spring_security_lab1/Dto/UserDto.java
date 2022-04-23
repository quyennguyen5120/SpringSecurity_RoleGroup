package com.example.spring_security_lab1.Dto;

import com.example.spring_security_lab1.Entity.Role;
import com.example.spring_security_lab1.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private List<RoleDto> roles;

    public UserDto(UserEntity u){
        this.username = u.getUsername();
        this.roles = u.getRoless().stream().map(x->{
            return new RoleDto(x);
        }).collect(Collectors.toList());
    }
}
