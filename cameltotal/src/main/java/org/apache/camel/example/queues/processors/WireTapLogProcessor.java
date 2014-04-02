package org.apache.camel.example.queues.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dmitry.bilyk on 3/12/14.
 */
public class WireTapLogProcessor implements Processor{
    Logger logger = LoggerFactory.getLogger(WireTapLogProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("from wire tap log processor " + exchange.getIn().getBody(String.class));
    }
}
