package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.UserRoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRoleEntity, Integer> {
}
