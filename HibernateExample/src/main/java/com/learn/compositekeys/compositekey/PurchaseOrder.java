package com.learn.compositekeys.compositekey;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * AccountUser: Dmitry
 * Date: 26.05.13
 * Time: 8:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PURCHASE_ORDERS")
public class PurchaseOrder {

//    private String street;
//    private String city;
    @Column(name = "ITEMNAME")
    private String itemName;

//    @Id

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Id
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name="STREET222")),
            @AttributeOverride(name = "city",
                    column = @Column(name="CITY222"))
    })

    private BillingAddress billingAddress;

    public String getItemName() {
        return itemName;
    }

//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return itemName + " "+ billingAddress.getCity()+ " " + billingAddress.getStreet();    }
}