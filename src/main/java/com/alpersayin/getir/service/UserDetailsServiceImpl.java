package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserEntity user = userEntityService.findByUserName(userName);
        return new org.springframework.security.core.userdetails.User(
                userName,
                "",
                true, true, true, true, getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
        List<UserRoleEntity> roles = new ArrayList<>(user.getRoles());
        List<String> list = roles.stream().map(e -> e.getRole().toString()).collect(Collectors.toList());
        return list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
