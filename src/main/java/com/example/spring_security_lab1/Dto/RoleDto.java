package com.example.spring_security_lab1.Dto;

import com.example.spring_security_lab1.Entity.Permission;
import com.example.spring_security_lab1.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String name;
    private List<String> permissions;

    public RoleDto(Role r){
        this.name = r.getName();
        List<String> Contain = new ArrayList<>();
        List<Permission> lst = new ArrayList<>();
        lst.addAll(r.getPermissions());
        for(int i=0; i < lst.size();i++){
            Contain.add(lst.get(i).getName());
        }
        this.permissions = Contain;
    }
}
