package com.regmi.auth.rest.controller;

import com.regmi.auth.exception.BadRequestException;
import com.regmi.auth.exception.UnauthorizedException;
import com.regmi.auth.model.RoleEntity;
import com.regmi.auth.model.UserEntity;
import com.regmi.auth.rest.payload.request.PasswordChanger;
import com.regmi.auth.rest.payload.response.UserResponse;
import com.regmi.auth.security.service.UserDetailsServiceImpl;
import com.regmi.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserDetailsServiceImpl userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {
        UserEntity user = userService.getUserById(id);
        UserEntity userEntity = userService.getUserById(id);
        return new UserResponse(userEntity.getId(), userEntity.getUsername(),
                userEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public void DeleteUser(@PathVariable int id) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.equals(userService.getUserById(id).getUsername())) {
            userService.deleteUserById(id);
        } else {
            throw new UnauthorizedException("You are not authorized for user id-" + id);
        }
    }

    @PutMapping("/{id}/change-password")
    public void updatePassword(@RequestBody PasswordChanger passwordChanger, @PathVariable int id) {
        UserEntity userEntity = userService.getUserById(id);
        UserEntity currentUser = userService.getUserByName((String) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal()).get();
        if (!currentUser.getUsername().equals(userEntity.getUsername())) {
            throw new UnauthorizedException("You are not authorized for user id-" + id);
        } else if (!passwordEncoder.matches(passwordChanger.getCurrentPass(),currentUser.getPassword())) {
            throw new BadRequestException("Invalid Credentials");
        } else {
            userEntity.setPassword(passwordChanger.getNewPass());
            userService.updateUser(userEntity);
        }
    }
}

