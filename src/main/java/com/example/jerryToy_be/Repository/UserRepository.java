package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
