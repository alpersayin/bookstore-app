package com.alpersayin.repository;

import com.alpersayin.entity.enums.UserRole;
import com.alpersayin.entity.UserRoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRoleEntity, String> {
    Optional<UserRoleEntity> findByRole(UserRole name);
}
