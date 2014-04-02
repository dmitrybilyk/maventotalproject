package com.learn.testing.easymock;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 30.09.12
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public interface PricingService {
    void setDataAccess(DataAccess dataAccess);
    BigDecimal getPrice(String sku) throws SkuNotFoundException;
}
