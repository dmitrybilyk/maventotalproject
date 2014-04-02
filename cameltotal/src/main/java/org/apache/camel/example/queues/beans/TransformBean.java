package org.apache.camel.example.queues.beans;

public class TransformBean {
    public static String map(String custom) {
        return custom + " - transformed with bean!";
    }
}
