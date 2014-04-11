package com.complex.model.document;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */

import javax.persistence.*;

@Entity
@Table(name="DOCUMENTS")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE) //Least normalisation strategy
@DiscriminatorColumn(
        name="DTYPE",
        discriminatorType=DiscriminatorType.STRING)
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="DOCUMENT_ID")
    private int id;
    private String name;
    private DocumentType documentType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
