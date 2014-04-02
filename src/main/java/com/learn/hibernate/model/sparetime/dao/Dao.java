package com.learn.hibernate.model.sparetime.dao;

import com.learn.hibernate.model.sparetime.Address;
import com.learn.hibernate.model.sparetime.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.07.12
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
public interface Dao<T> {
    public void save();

    List<T> getEntityList();

    List<T> getEntityLikeName(String pattern);

    void add200Entities();

    List<T> getAddressOfUser(String s);

    List<T> getUsersByExample();

    void getUsersByProjection();

    int size();

    Double averageAge();

    void groupByMethod();

    List<User> testDetachedCriteria();

    List<Address> selectAddressWithUser();
}
