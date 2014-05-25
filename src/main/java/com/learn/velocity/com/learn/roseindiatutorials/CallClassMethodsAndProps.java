package com.learn.velocity.com.learn.roseindiatutorials;

import java.io.*;
import java.util.*;

import org.apache.velocity.*;
import org.apache.velocity.app.*;

public class CallClassMethodsAndProps {

  public static void main(String args[]) throws Exception {
  Velocity.init();
  Template t = 
  Velocity.getTemplate("./src/main/resources/velocity/ClassMethodsAndProperties.vm");

  VelocityContext ctx = new VelocityContext();

  ctx.put("date", new Date());
  Writer writer = new StringWriter();
  t.merge(ctx, writer);

  System.out.println(writer);
  }
}