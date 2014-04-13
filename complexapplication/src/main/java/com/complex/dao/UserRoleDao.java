package com.complex.dao;

import com.complex.model.security.UserRole;

import java.util.List;

/**
 * Created by dmitry on 13.04.14.
 */
public interface UserRoleDao {
    public List getAll();

    UserRole getById(int userRoleId);
}
