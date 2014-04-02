package com.learn.bases.xml.xmldomtest;

import java.io.*;

import org.jdom.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
//import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DomTest {
    public static void main(String[] args) {
        try {
            String userHomeVar = System.getProperty("user.home");
            String fileSeparator = System.getProperty("file.separator");
            String AbbyyPath = fileSeparator+"ABBYY"+fileSeparator+"Tutor"+fileSeparator+"3.0"+fileSeparator;

            String path;
            String windowsSevenPath = "AppData"+fileSeparator+"Local";
            String windowsXPPath = "Local settings"+fileSeparator+"Application Data";
            if(System.getProperty("os.name").contains("XP")){
               path = windowsXPPath;
            }else{
               path = windowsSevenPath;
            }
            String contextPath = path + AbbyyPath;


            File xmlDir = new File(userHomeVar+fileSeparator+contextPath);

            if(xmlDir.isDirectory()){
                File[] files = xmlDir.listFiles();
                for (File file : files) {
                    if(file.getName().contains("OWN")){
                        Element cardlist = new Element("cardlist");
                        String inpath = "MYOWN(En-Ru)";
                        cardlist.setAttribute(new Attribute("name", inpath));
                        Document document = new Document(cardlist);
                        document.setRootElement(cardlist);

                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        org.w3c.dom.Document doc = db.parse(file);
                        doc.getDocumentElement().normalize();
                        NodeList nodeLst = doc.getElementsByTagName("card");
                        for (int s = 0; s < nodeLst.getLength(); s++){
                            Node cardElement = nodeLst.item(s);
                            String englishVersion = cardElement.getFirstChild().getFirstChild().getNodeValue(); //permanent
                            String russianVersion = cardElement.getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getTextContent();
                            String example = cardElement.getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getTextContent();
                            Element card = new Element("card");
                            card.setAttribute(new Attribute("sides", "2"));
                            Element side1Element = new Element("side");
                            side1Element.setAttribute(new Attribute("type", "txt"));
                            card.addContent(side1Element.setText(englishVersion));

                            Element side2Element = new Element("side");
                            side2Element.setAttribute(new Attribute("type", "txt"));

                            card.addContent(side2Element.setText(russianVersion + " |"+example));
                            document.getRootElement().addContent(card);

                        }
                        XMLOutputter xmlOutput = new XMLOutputter();
                        xmlOutput.setFormat(Format.getRawFormat());
                        String cutFileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        Writer out = new BufferedWriter(new OutputStreamWriter(

                                new FileOutputStream("d:\\"+cutFileName+"_toLearnByHeart.mcl"), "UTF8"));
                        xmlOutput.output(document, out);

                        }

                    }

            }

            System.out.println("File updated!");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
