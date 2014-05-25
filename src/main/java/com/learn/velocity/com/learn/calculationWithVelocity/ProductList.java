package com.learn.velocity.com.learn.calculationWithVelocity;

import java.io.*;
import java.util.*;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class ProductList {

  public static void main(String args[]) throws Exception {
  Velocity.init();

  Template template = 
   Velocity.getTemplate("./src/main/resources/velocity/calculationWithVelocity/Product.vm");

  VelocityContext context = new VelocityContext();

  ArrayList list = new ArrayList();
  list.add(new Product("Product 1", 111.99));
  list.add(new Product("Product 2", 222.89));
  list.add(new Product("Product 3", 333.79));
  list.add(new Product("Product 4", 444.69));

  context.put("productList", list);

  Iterator iterator = list.iterator();

  Product p = null;
  double total = 0.00;
  
  while (iterator.hasNext()) {
  p = (Product) iterator.next();
  total+=p.getPrice();
  }
  context.put("totalPrice", new Double(total));

  Writer writer = new StringWriter();
  template.merge(context, writer);

  System.out.println(writer);
  }
}