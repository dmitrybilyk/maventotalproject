package com.learn.bases.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bid on 6/4/14.
 */
public class MainTest {
   public static void main(String[] args) {
      List<String> list = new ArrayList<String>();
      list.add("first");
      list.add("second");
      list.add("third");
      System.out.println(list.toString());
   }
}
