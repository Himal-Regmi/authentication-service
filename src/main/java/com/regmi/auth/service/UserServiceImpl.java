package com.regmi.auth.service;

import com.regmi.auth.dao.UserDao;
import com.regmi.auth.exception.BadRequestException;
import com.regmi.auth.exception.UserNotFoundException;
import com.regmi.auth.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserEntity getUserById(int id) {
        UserEntity user = userDao.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id-" + id + " not found.");
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(UserEntity user) {
        if (userDao.getUserByName(user.getUsername()) != null) {
            throw new BadRequestException("Username " + user.getUsername() + " is taken.");
        }
        UserEntity userEntity = new UserEntity(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(user.getRoles());
        userDao.saveUser(userEntity);
    }

    @Override
    @Transactional
    public Optional<UserEntity> getUserByName(String name) {
        UserEntity user = userDao.getUserByName(name);
        if (user == null) {
            throw new UserNotFoundException("User with name-" + name + " not found.");
        }
        return Optional.of(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }
}
