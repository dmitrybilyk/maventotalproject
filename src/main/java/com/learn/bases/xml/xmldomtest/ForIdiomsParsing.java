package com.learn.bases.xml.xmldomtest;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 13.10.12
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public class ForIdiomsParsing {
    private static final String openTag = "<tr>";
    private static final String closeTag = "</tr>";
    public static void main(String[] args) {
        try {
            File xmlDir = new File("d://");

            File[] files = xmlDir.listFiles();
            for (File file : files) {
                Document document = null;
                if (file.getName().contains("idioms")) {
                    Element cardlist = new Element("cardlist");
                    String inpath = "MYOWN(En-Ru)";
                    cardlist.setAttribute(new Attribute("name", inpath));
                    document = new Document(cardlist);
                    document.setRootElement(cardlist);

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    boolean tagIsOpened = false;
                    boolean englishVersionSaved = false;
                    boolean russianVersionSaved = false;
                    boolean rowPassed = false;
                    String englishVersion = null;
                    String russianVersion = null;
                    for(String rLine = br.readLine(); rLine!=null; rLine = br.readLine()){
                        if(rLine.equals(closeTag)){
                            continue;
                        }
                        if(rLine.equals(openTag)){
                           tagIsOpened = true;
                            continue;
                        }
                        if(tagIsOpened){
                           englishVersion = cutTDs(rLine);
                           englishVersionSaved = true;
                            tagIsOpened = false;
                            continue;
                        }
                        if(englishVersionSaved){
                            rowPassed = true;
                            englishVersionSaved = false;
                            continue;
                        }
                        if(rowPassed){
                            russianVersion = cutTDs(rLine);
                            rowPassed = false;
                        }

                        Element card = new Element("card");
                        card.setAttribute(new Attribute("sides", "2"));
                        Element side1Element = new Element("side");
                        side1Element.setAttribute(new Attribute("type", "txt"));
                        card.addContent(side1Element.setText(englishVersion));

                        Element side2Element = new Element("side");
                        side2Element.setAttribute(new Attribute("type", "txt"));

                        card.addContent(side2Element.setText(russianVersion));
                        document.getRootElement().addContent(card);
                    }
                } else{
                    continue;
                }


                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getRawFormat());
                String cutFileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                Writer out = new BufferedWriter(new OutputStreamWriter(

                        new FileOutputStream("d:\\" + cutFileName + "_toLearnByHeart.mcl"), "UTF8"));
                xmlOutput.output(document, out);
//                        }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("File updated!");

    }

    private static String cutTDs(String rLine) {
        return rLine.substring(rLine.indexOf('>')+1, rLine.lastIndexOf('<'));
    }
}
