package com.complex.dao.impl;

import com.complex.dao.DocumentDao;
import com.complex.model.document.Document;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Transactional
@Repository
public class DocumentDaoImpl implements DocumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createDocument(Document document) {
        sessionFactory.getCurrentSession().save(document);
    }
}
