package com.learn.testing.jmock;
import com.learn.testing.easymock.SkuNotFoundException;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 21.03.13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public interface PricingService {
    void setDataAccess(DataAccess dataAccess);
    BigDecimal getPrice(String sku) throws SkuNotFoundException;
}
