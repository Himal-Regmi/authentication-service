package com.regmi.auth.dao;

import com.regmi.auth.model.RoleEntity;

public interface RoleDao {
    RoleEntity getRoleByName(String name);
}
