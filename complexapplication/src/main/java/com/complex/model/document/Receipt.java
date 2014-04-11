package com.complex.model.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Entity
@Table(name="RECEIPTS")
@DiscriminatorValue("R")
public class Receipt extends Document{
}
