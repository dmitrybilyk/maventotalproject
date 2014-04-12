package com.complex.model.document;

import com.complex.model.Payer;
import com.complex.model.Receiver;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
@Entity
//@Table(name="RECEIPTS")
@DiscriminatorValue("R")
public class Receipt extends Document implements Cloneable{
    @OneToOne
    private Payer payer;
    @OneToOne
    private Receiver receiver;

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
