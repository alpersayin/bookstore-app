package com.alpersayin.getir.entity;

import com.alpersayin.getir.entity.enums.UserRole;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity extends BaseEntity {
    @Indexed(unique=true)
    private String userName;
    private String password;
    @Indexed(unique=true)
    private String email;
    private Set<UserRoleEntity> roles = new HashSet<>();
}
