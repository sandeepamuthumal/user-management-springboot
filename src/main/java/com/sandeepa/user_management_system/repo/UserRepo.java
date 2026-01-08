package com.sandeepa.user_management_system.repo;

import com.sandeepa.user_management_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(String email);
    List<User> findAllByStatusTrueOrderByCreatedAtDesc();
    Page<User> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "userType")
    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
