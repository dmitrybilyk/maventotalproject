package com.learn.compositekeys.compositekey;

import javax.persistence.*;

/**
 * Created by dmitry.bilyk on 3/24/14.
 */

@Entity(name = "Students")
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Id
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name="StudentsStreet")),
            @AttributeOverride(name = "city",
                    column = @Column(name="StudentCity"))
    })

    private BillingAddress billingAddress;

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
}
