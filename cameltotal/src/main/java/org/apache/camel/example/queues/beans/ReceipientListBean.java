package org.apache.camel.example.queues.beans;

import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class ReceipientListBean {
    @RecipientList
    public String[] route(@XPath("/order/@customer") String customer) {
        if (isGoldCustomer(customer)) {
            return new String[] {"my-jms:MyAccounting", "my-jms:MyProduction"};
        } else {
            return new String[] {"my-jms:MyAccounting"};
        }
    }
    private boolean isGoldCustomer(String customer) {
        return customer.equals("honda");
    }

}
