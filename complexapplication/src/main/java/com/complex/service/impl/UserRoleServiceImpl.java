package com.complex.service.impl;

import com.complex.dao.UserRoleDao;
import com.complex.model.security.UserRole;
import com.complex.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitry on 13.04.14.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> getAll() {
        return userRoleDao.getAll();
    }

    @Override
    public UserRole getUserRoleById(int userRoleId) {
        return userRoleDao.getById(userRoleId);
    }
}
