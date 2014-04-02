package com.learn.testing.easymock;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 30.09.12
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class PricingServiceImpl implements PricingService{
    private DataAccess dataAccess;
    @Override
    public void setDataAccess(DataAccess dataAccess) {
         this.dataAccess = dataAccess;
     }

     public BigDecimal getPrice(String sku) throws SkuNotFoundException {
         BigDecimal price = this.dataAccess.getPriceBySku(sku);
         if (price == null) {
             throw new SkuNotFoundException("SKU not found.");
         }
         return price;
     }
}