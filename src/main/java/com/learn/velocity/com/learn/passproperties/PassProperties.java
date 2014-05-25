package com.learn.velocity.com.learn.passproperties;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PassProperties {

  public static void main(String[] args) throws Exception {
  Properties props = new Properties();
  props.put("input.encoding", "utf-8");

  Velocity.init(props);

  Template template = Velocity.getTemplate("./src/main/resources/velocity/passproperties/PassProperties.vm");

  VelocityContext context = new VelocityContext();

  Writer writer = new StringWriter();
  template.merge(context, writer);

  System.out.println(writer.toString());
  }
}