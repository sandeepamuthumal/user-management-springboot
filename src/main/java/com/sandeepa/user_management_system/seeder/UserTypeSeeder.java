package com.sandeepa.user_management_system.seeder;

import com.sandeepa.user_management_system.model.UserType;
import com.sandeepa.user_management_system.repo.UserTypeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserTypeSeeder implements CommandLineRunner {
    private final UserTypeRepo userTypeRepo;

    public UserTypeSeeder(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

    @Override
    public void run(String... args) {

        seedUserType("ADMIN");
        seedUserType("USER");
        seedUserType("MODERATOR");
    }

    private void seedUserType(String name) {
        if (!userTypeRepo.existsByName(name)) {
            UserType userType = new UserType();
            userType.setName(name);
            userType.setStatus(true);
            userTypeRepo.save(userType);
        }
    }
}
