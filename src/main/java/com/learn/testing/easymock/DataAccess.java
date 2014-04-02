package com.learn.testing.easymock;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 30.09.12
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public interface DataAccess {
    BigDecimal getPriceBySku(String sku);
}
