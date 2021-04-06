package com.regmi.auth.service;

import com.regmi.auth.model.UserEntity;
import java.util.Optional;

public interface UserService {
    UserEntity getUserById(int id);

    void saveUser(UserEntity user);

    Optional<UserEntity> getUserByName(String name);

    void deleteUserById(int id);

    void updateUser(UserEntity user);
}
