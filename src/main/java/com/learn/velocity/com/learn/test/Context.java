package com.learn.velocity.com.learn.test;

import java.util.List;

/**
 * Created by bid on 5/29/14.
 */
public class Context {
    private boolean                          hasToBeDeliveredProducts;

    private String miles;

    private List<Bonus> bonusList;

    private AddressModel invoiceAddress;

    public AddressModel getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(AddressModel invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public boolean isHasToBeDeliveredProducts() {
        return hasToBeDeliveredProducts;
    }

    public void setHasToBeDeliveredProducts(boolean hasToBeDeliveredProducts) {
        this.hasToBeDeliveredProducts = hasToBeDeliveredProducts;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }
}
