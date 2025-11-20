package com.sandeepa.user_management_system.repo;

import com.sandeepa.user_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    List<User> findAllByStatusTrueOrderByCreatedAtDesc();
    List<User> findAllByOrderByCreatedAtDesc();
}
