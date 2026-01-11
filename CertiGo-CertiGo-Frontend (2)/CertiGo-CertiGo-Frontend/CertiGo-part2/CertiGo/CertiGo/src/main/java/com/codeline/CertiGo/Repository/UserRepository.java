package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.isActive = true")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isActive = true")
    Optional<User> getUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isActive = true")
    User getUserById(Integer id);
}
