package com.learn.testing.test.junittest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class SubscriptionTest {

   @Test
   public void test_returnEuro() {
      System.out.println("Test if pricePerMonth returns Euro...") ;
      Subscription S = new Subscription(200,2) ;
      assertTrue(S.pricePerMonth() == 100.0) ;
   }

   @Test
   public void test_roundUp() {
      System.out.println("Test if pricePerMonth rounds up correctly...") ;
      Subscription S = new Subscription(200,3) ;
      assertTrue(S.pricePerMonth() == 66) ;
   }

    @Test
    public void testEmptyCollection() {
        Collection collection = new ArrayList();
        assertTrue(collection.isEmpty());
    }
}