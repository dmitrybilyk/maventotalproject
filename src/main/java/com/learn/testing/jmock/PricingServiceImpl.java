package com.learn.testing.jmock;

import com.learn.testing.easymock.SkuNotFoundException;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 21.03.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class PricingServiceImpl implements PricingService {
private DataAccess dataAccess;

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
}

     public BigDecimal getPrice(String sku) throws SkuNotFoundException {
         final BigDecimal price = this.dataAccess.getPriceBySku(sku);
      if (price == null) {
            throw new SkuNotFoundException();
       }
             return price;
   }
}