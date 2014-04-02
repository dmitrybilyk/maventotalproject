package com.learn;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
public class GetSoapDataProcessor implements Processor{
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());
    }
}
