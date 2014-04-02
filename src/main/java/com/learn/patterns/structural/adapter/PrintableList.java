package com.learn.patterns.structural.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
//Target. defines domains-specific interface client uses.
import java.util.ArrayList;

public interface PrintableList {
    void printList(ArrayList<String> list);
}