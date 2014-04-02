package com.learn.testing.jmock;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 21.03.13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public interface DataAccess {
    BigDecimal getPriceBySku(String sku);
}
