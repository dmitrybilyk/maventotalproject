package org.apache.camel.example.queues;

import org.apache.camel.Exchange;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class TestBean {
    public void pringExchangeInfo(Exchange exchange){
        System.out.println("We just downloaded - log from bean : " + exchange.getIn().getHeader("CamelFileName"));
    }
}
