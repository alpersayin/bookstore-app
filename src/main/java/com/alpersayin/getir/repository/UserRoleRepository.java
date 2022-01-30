package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.UserRoleEntity;
import com.alpersayin.getir.entity.enums.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRoleEntity, String > {
    Optional<UserRoleEntity> findByRole(UserRole name);
}
