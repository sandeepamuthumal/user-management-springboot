package com.sandeepa.user_management_system.specification;

import com.sandeepa.user_management_system.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasStatus(Boolean status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<User> hasUserType(String userType) {
        return (root, query, cb) ->
                userType == null ? null :
                        cb.equal(root.get("userType").get("name"), userType);
    }

    public static Specification<User> hasSearch(String keyword) {
        return (root, query, cb) ->
                keyword == null ? null :
                        cb.or(
                                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                                cb.like(cb.lower(root.get("email")), "%" + keyword.toLowerCase() + "%")
                        );
    }
}
