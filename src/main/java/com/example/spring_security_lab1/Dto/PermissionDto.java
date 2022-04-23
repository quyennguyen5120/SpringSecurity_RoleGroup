package com.example.spring_security_lab1.Dto;

import com.example.spring_security_lab1.Entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {
    private String name;

    public PermissionDto(Permission p){
        this.name = p.getName();
    }
}
