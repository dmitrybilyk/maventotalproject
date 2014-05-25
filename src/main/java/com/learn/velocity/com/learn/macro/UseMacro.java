package com.learn.velocity.com.learn.macro;

import org.apache.velocity.*;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.RenderTool;

import java.io.StringWriter;
import java.io.Writer;

public class UseMacro {

  public static void main(String[] args) throws Exception {
  Velocity.init();
  Template template = Velocity.getTemplate("./src/main/resources/velocity/macro/UseMacro.vm");

  VelocityContext context = new VelocityContext();

  Writer writer = new StringWriter();
  template.merge(context, writer);

  System.out.println(writer);
  }
}