package com.regmi.auth.dao;

import com.regmi.auth.model.UserEntity;

public interface UserDao {
    UserEntity getUserById(int id);

    void saveUser(UserEntity user);

    UserEntity getUserByName(String name);

    void deleteUserById(int id);

    void updateUser(UserEntity user);

}
