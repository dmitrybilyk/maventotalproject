package com.complex.dao.impl;

import com.complex.dao.UserRoleDao;
import com.complex.model.security.UserRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dmitry on 13.04.14.
 */
@Transactional
@Repository
public class UserRoleDaoImpl implements UserRoleDao{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<UserRole> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserRole").list();
    }

    @Override
    public UserRole getById(int userRoleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserRole where userRoleId = :id ");
        query.setParameter("id", userRoleId);
        UserRole userRole = (UserRole) query.uniqueResult();
        return userRole;
    }
}
