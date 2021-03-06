package com.alpersayin.service;

import com.alpersayin.entity.enums.UserRole;
import com.alpersayin.exception.ApiNotFoundException;
import com.alpersayin.exception.ApiRequestException;
import com.alpersayin.entity.CustomerEntity;
import com.alpersayin.entity.UserEntity;
import com.alpersayin.entity.UserRoleEntity;
import com.alpersayin.mapper.CustomerMapper;
import com.alpersayin.payload.request.LoginRequest;
import com.alpersayin.payload.request.RegisterRequest;
import com.alpersayin.payload.response.LoginResponse;
import com.alpersayin.payload.response.Response;
import com.alpersayin.repository.CustomerRepository;
import com.alpersayin.repository.UserRepository;
import com.alpersayin.repository.UserRoleRepository;
import com.alpersayin.security.JwtProvider;
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
public class CustomerServiceImpl implements CustomerService {

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

        // Check if UserRole exist in DB for case study.
        checkAndPersistUserRole();

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

        return new Response("User registered successfully!");
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

    @Override
    public CustomerEntity findByCustomerId(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Customer Id with: " + id + " not found."));
    }

    private void checkAndPersistUserRole() {
        if (userRoleRepository.findAll().size() > 0) {
               System.out.println("User Roles already exists.");
        } else persistUserRoles(userRoleRepository);
    }

    private static void persistUserRoles(UserRoleRepository userRoleRepository) {
        UserRoleEntity role1 = new UserRoleEntity(
                UserRole.ROLE_ADMIN
        );
        UserRoleEntity role2 = new UserRoleEntity(
                UserRole.ROLE_CUSTOMER
        );
        UserRoleEntity role3 = new UserRoleEntity(
                UserRole.ROLE_USER
        );
        List<UserRoleEntity> userRoleEntityList = Arrays.asList(role1,role2,role3);
        userRoleRepository.saveAll(userRoleEntityList);
    }

}
