package com.learn.hibernate.model.sparetime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.07.12
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private long id;
    private Long amount;
    @ManyToOne
    private Payer payer;

    private PaymentPeriodType paymentPeriodType;

    public PaymentPeriodType getPaymentPeriodType() {
        return paymentPeriodType;
    }

    public void setPaymentPeriodType(PaymentPeriodType paymentPeriodType) {
        this.paymentPeriodType = paymentPeriodType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
