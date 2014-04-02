package org.apache.camel.example.queues.filter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class FilterLogger implements Processor{
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Filtered xml: " + exchange.getIn().getBody(String.class));
    }
}
