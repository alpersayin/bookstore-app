package com.alpersayin.getir.entity;

import com.alpersayin.getir.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
