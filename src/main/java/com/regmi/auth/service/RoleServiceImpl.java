package com.regmi.auth.service;

import com.regmi.auth.dao.RoleDao;
import com.regmi.auth.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public RoleEntity getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
