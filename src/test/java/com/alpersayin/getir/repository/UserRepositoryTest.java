package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.entity.UserRoleEntity;
import com.alpersayin.getir.entity.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldCheckIfUserExistsByUserName() {
        // given
        Set<UserRoleEntity> roles = new HashSet<>();
        roles.add(new UserRoleEntity(
                UserRole.ROLE_USER
        ));
        String userName = "alpersayin";
        UserEntity user = new UserEntity(
                userName,
                "123456",
                "alper@mail.com",
                roles
        );
        underTest.save(user);
        // when
        boolean expected = underTest.existsByUserName(userName);
        // then
        assertThat(expected).isTrue();
    }

}