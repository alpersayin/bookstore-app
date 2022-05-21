package com.alpersayin.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity extends BaseEntity {
    private String userName;
    private String password;
    private String email;
    @DBRef
    private Set<UserRoleEntity> roles = new HashSet<>();
}
