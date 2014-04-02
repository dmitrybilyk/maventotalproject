/**
 * 
 */
package org.apache.camel.example.queues;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DownloadLogger implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
