package com.alpersayin.getir;

import com.alpersayin.getir.entity.UserRoleEntity;
import com.alpersayin.getir.entity.enums.UserRole;
import com.alpersayin.getir.repository.BookRepository;
import com.alpersayin.getir.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ReadingIsGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingIsGoodApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRoleRepository userRoleRepository) {
        return args -> {
            if (userRoleRepository.findAll().size() > 0) {
                System.out.println("User Roles already exists.");
            } else persistUserRoles(userRoleRepository);

        };
    }

    private static void persistUserRoles(UserRoleRepository userRoleRepository) {
        UserRoleEntity role1 = new UserRoleEntity(
                UserRole.ROLE_ADMIN
        );
        UserRoleEntity role2 = new UserRoleEntity(
                UserRole.ROLE_CUSTOMER
        );
        UserRoleEntity role3 = new UserRoleEntity(
                UserRole.ROLE_USER
        );
        List<UserRoleEntity> userRoleEntityList = Arrays.asList(role1,role2,role3);
        userRoleRepository.saveAll(userRoleEntityList);
    }

}
