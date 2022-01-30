package com.alpersayin.getir.mapper;

import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.entity.UserRoleEntity;
import com.alpersayin.getir.payload.request.RegisterRequest;
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
