package com.learn.velocity.com.learn.wraphtml;

import org.apache.velocity.*;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.IteratorTool;

import java.io.StringWriter;
import java.io.Writer;

public class MacroToWrapHTML {

  public static void main(String[] args) throws Exception {
  Velocity.init();

  Template template = Velocity.getTemplate("./src/main/resources/velocity/macroToWrapHtml/MacroToWrapHtml.vm");

  VelocityContext context = new VelocityContext();
  context.put("var", new MacroToWrapHTML());

  Writer writer = new StringWriter();
  template.merge(context, writer);

  System.out.println(writer);
  }
} 