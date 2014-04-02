package org.apache.camel.example.queues;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.example.queues.model.Student2;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class MyQueueProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange);
        Student2 student = exchange.getIn().getBody(Student2.class);
    }
}
