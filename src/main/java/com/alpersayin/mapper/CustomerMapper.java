package com.alpersayin.mapper;

import com.alpersayin.entity.CustomerEntity;
import com.alpersayin.entity.UserEntity;
import com.alpersayin.entity.UserRoleEntity;
import com.alpersayin.payload.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    @Mapping(target = "password", source = "encodedPassword")
    @Mapping(target = "roles", source = "roles")
    public abstract UserEntity mapToUser(RegisterRequest registerRequest, String encodedPassword, Set<UserRoleEntity> roles);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public abstract CustomerEntity mapToCustomer(RegisterRequest registerRequest);

}
