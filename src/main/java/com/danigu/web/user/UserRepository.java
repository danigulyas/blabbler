package com.danigu.web.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dani
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
