package com.learn.bases.xml.xmldomtest.jaxbTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 30.09.12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class XmlToObject {
    public static void main(String[] args) {
         try {

		File file = new File("C:\\file.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
		System.out.println(customer.getName());

	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
    }
}
