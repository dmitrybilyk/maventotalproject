package com.learn.velocity.com.learn.usemap;

import java.io.*;
import java.util.*;
import org.apache.velocity.*;
import org.apache.velocity.app.*;

public class MapClass {

  public static void main(String[] args)
  throws Exception {
  VelocityEngine ve = new VelocityEngine();
  ve.init();

  ArrayList list = new ArrayList();
  Map map = new HashMap();

  map.put("rno", "1");
  map.put("name", "komal");
  map.put("libno", 1001);
  list.add(map);

  map = new HashMap();
  map.put("rno", "2");
  map.put("name", "santosh");
  map.put("libno", 1002);
  list.add(map);

  map = new HashMap();
  map.put("rno", "3");
  map.put("name", "ajay");
  map.put("libno", 1003);
  list.add(map);

  VelocityContext context = new VelocityContext();
  context.put("stuList", list);
  Template t = ve.getTemplate("./src/main/resources/velocity/usemap/map.vm");
  StringWriter writer = new StringWriter();

  t.merge(context, writer);
  System.out.println(writer.toString());
  }
}