package com.example.spring_security_lab1.Repository;

import com.example.spring_security_lab1.Dto.UserDto;
import com.example.spring_security_lab1.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoty extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query("select new com.example.spring_security_lab1.Dto.UserDto(u) from UserEntity u where u.username = ?1 and u.password =?2")
    public UserDto findByUserPass(String username, String password);
}
