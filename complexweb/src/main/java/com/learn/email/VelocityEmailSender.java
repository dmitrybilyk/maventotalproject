package com.learn.email;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Component
public class VelocityEmailSender implements Sender { 

//    private static final Logger logger = LoggerFactory.getLogger(VelocityEmailSender.class);

    private final VelocityEngine velocityEngine;
    private final JavaMailSender mailSender;

    /**
     * Constructor
     */
    @Autowired
    public VelocityEmailSender(VelocityEngine velocityEngine, 
                               JavaMailSender mailSender) {
        this.velocityEngine = velocityEngine;
        this.mailSender = mailSender;
    }

    /**
     * Sends e-mail using Velocity template for the body and 
     * the properties passed in as Velocity variables.
     * 
     * @param   msg                 The e-mail message to be sent, except for the body.
     * @param   hTemplateVariables  Variables to use when processing the template. 
     */
    public void send(final SimpleMailMessage msg, 
                     final Map<String, Object> hTemplateVariables) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               message.setTo(msg.getTo());
               message.setFrom(msg.getFrom());
               message.setSubject(msg.getSubject());

               String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "emailBody.vm", hTemplateVariables);

                System.out.println(body);

               message.setText(body, true);
            }
         };
         
         mailSender.send(preparator);

        System.out.println("Sent e-mail to '{}'."  + msg.getTo());
    }
    
}