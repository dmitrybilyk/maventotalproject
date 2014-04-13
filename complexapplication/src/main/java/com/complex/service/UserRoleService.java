package com.complex.service;

import com.complex.model.security.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitry on 13.04.14.
 */
public interface UserRoleService {
    public List<UserRole> getAll();
    UserRole getUserRoleById(int userRoleId);
}
