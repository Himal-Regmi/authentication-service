package com.regmi.auth.service;

import com.regmi.auth.model.RoleEntity;

public interface RoleService {
    RoleEntity getRoleByName(String name);
}
