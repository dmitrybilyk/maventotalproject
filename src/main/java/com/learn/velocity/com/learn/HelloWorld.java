package com.learn.velocity.com.learn;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class HelloWorld {
    public static void main(String[] args) throws Exception {

/*STEP 1 :Create a VelocityTemplate Engine Object*/
        VelocityEngine velocityEngine = new VelocityEngine();
/*STEP 2 :Initialise VelocityTemplate Engine */
        velocityEngine.init();
/*
* NOTE : we can directly initialise the velocity engine by calling "Velocity.init()"
* Creation of VelocityEngine object is available from 1.2 version only
* */
/*STEP 3: Create the template object by calling getTemplate() method in VE(VelocityEngine)*/
        Template template = velocityEngine.getTemplate("/home/bid/Dev/projects/maventotalproject/src/main/resources/velocity/helloworld.txt");
/*STEP 4: Create VelocityContext */
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();
/*STEP 5: Process the template and write the o/p to stream*/
        template.merge(context, writer);
/*STEP 6: Show the output */
        System.out.println(writer);
    }
}