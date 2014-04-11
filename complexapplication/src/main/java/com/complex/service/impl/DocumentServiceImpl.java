package com.complex.service.impl;

import com.complex.dao.DocumentDao;
import com.complex.model.document.Document;
import com.complex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;

    @Override
    public void createDocument(Document document) {
        documentDao.createDocument(document);
    }
}
