package com.example.spring_security_lab1.Repository;

import com.example.spring_security_lab1.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoty extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
