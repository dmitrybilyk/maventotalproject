package com.complex.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */
public class Receiver {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="RECEIVER_ID")
    private int id;

    private ReceiverType receiverType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ReceiverType receiverType) {
        this.receiverType = receiverType;
    }
}
