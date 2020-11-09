package com.zerod.authdemo.repositories;

import com.zerod.authdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find user model by email
     * @param email email
     * @return the User model according to the email
     */
    Optional<User> findByEmail(String email);
}
