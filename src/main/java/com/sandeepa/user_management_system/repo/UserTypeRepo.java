package com.sandeepa.user_management_system.repo;

import com.sandeepa.user_management_system.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType,Long> {
    Optional<UserType> findByName(String name);
    boolean existsByName(String name);
}
