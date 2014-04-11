package com.complex.controller;

import com.complex.model.document.DirectDebit;
import com.complex.model.document.Document;
import com.complex.model.document.DocumentType;
import com.complex.model.document.Receipt;
import com.complex.service.DocumentService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Controller
@RequestMapping(value = "/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/create")
    public void createDocument(){
        Document receipt = new Receipt();
        receipt.setName("Receipt1");
        receipt.setDocumentType(DocumentType.RECEIPT);

        Document receipt2 = new Receipt();
        receipt2.setName("Receipt2");

        Document directDebit = new DirectDebit();
        directDebit.setName("dd1");

        documentService.createDocument(receipt);
        documentService.createDocument(receipt2);

        documentService.createDocument(directDebit);
    }
}
