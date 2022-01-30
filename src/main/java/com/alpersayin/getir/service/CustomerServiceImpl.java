package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.entity.UserRoleEntity;
import com.alpersayin.getir.entity.enums.UserRole;
import com.alpersayin.getir.exception.ApiRequestException;
import com.alpersayin.getir.mapper.CustomerMapper;
import com.alpersayin.getir.payload.request.LoginRequest;
import com.alpersayin.getir.payload.request.RegisterRequest;
import com.alpersayin.getir.payload.response.LoginResponse;
import com.alpersayin.getir.payload.response.Response;
import com.alpersayin.getir.repository.CustomerRepository;
import com.alpersayin.getir.repository.UserRepository;
import com.alpersayin.getir.repository.UserRoleRepository;
import com.alpersayin.getir.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  {

    private final UserEntityService userEntityService;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userEntityService.findByUserName(loginRequest.getUserName());
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        String token = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new ApiRequestException("Error occurred by validating user information.");
        }

        return LoginResponse.builder()
                .userName(user.getUserName())
                .token(token)
                .roles(getRolesFromUser(user))
                .expiresAt(getExpireDateTimeFromToken(token))
                .timestamp(LocalDateTime.now())
                .build();
    }

    private LocalDateTime getExpireDateTimeFromToken(String token) {
        return jwtProvider.extractExpiration(token)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
    }

    private List<String> getRolesFromUser(UserEntity user) {
        return new ArrayList<>(user.getRoles())
                    .stream()
                    .map(e -> e.getRole().toString())
                    .collect(Collectors.toList());
    }

    @Transactional
    public Response register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        if (userEntityService.existsByEmail(email)) {
            throw new ApiRequestException("Email is already in use.");
        }

        String username = registerRequest.getUserName();
        if (userEntityService.existsByUserName(username)) {
            throw new ApiRequestException("Username is already taken.");
        }

        UserEntity user = customerMapper.mapToUser(
                registerRequest,
                passwordEncoder.encode(registerRequest.getPassword()),
                getUserRoleEntities(registerRequest.getRoles())
        );
        CustomerEntity customer = customerMapper.mapToCustomer(registerRequest);

        userRepository.insert(user);
        customerRepository.insert(customer);

        return new Response(
             "User registered successfully!",
                LocalDateTime.now()
        );
    }

    private Set<UserRoleEntity> getUserRoleEntities(Set<String> strRoles) {
        List<String> strEnums = Arrays.asList(
                UserRole.ROLE_ADMIN.name(),
                UserRole.ROLE_CUSTOMER.name(),
                UserRole.ROLE_USER.name()
        );

        Set<UserRoleEntity> roles = new HashSet<>();
        for (String strRole : strRoles) {
            if (strEnums.contains(strRole)) {
                UserRoleEntity u = userRoleRepository.findByRole(UserRole.valueOf(strRole.toUpperCase()))
                        .orElseThrow(() -> new ApiRequestException("User Role : " + strRole + " is not found."));
                roles.add(u);
            }
        }
        return roles;
    }

}
