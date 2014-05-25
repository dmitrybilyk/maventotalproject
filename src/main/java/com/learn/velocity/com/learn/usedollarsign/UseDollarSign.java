package com.learn.velocity.com.learn.usedollarsign;

import java.io.*;

import org.apache.velocity.*;
import org.apache.velocity.app.*;
import org.apache.velocity.tools.generic.RenderTool;

public class UseDollarSign {

  public static void main(String[] args) throws Exception {
  Velocity.init();
  Template t = Velocity.getTemplate("./src/main/resources/velocity/usedollarsign/useDollarSign.vm");

  VelocityContext context = new VelocityContext();
  
  Writer writer = new StringWriter();
  t.merge(context, writer);

  System.out.println(writer);
  }
}