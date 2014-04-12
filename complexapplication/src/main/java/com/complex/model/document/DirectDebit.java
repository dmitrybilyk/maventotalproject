package com.complex.model.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Entity
//@Table(name="DIRECTDEBITS")
@DiscriminatorValue("D")
public class DirectDebit extends Document {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
