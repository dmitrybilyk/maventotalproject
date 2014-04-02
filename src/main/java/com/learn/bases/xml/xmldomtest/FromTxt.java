package com.learn.bases.xml.xmldomtest;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;

public class FromTxt {
    public static void main(String[] args) {
        try {
            File xmlDir = new File("e://words");

                File[] files = xmlDir.listFiles();
                for (File file : files) {
                    Document document = null;
                    if (file.getName().toUpperCase().contains("WORDS")) {
                        Element cardlist = new Element("cardlist");
                                                    String inpath = "MYOWN(En-Ru)";
                                                    cardlist.setAttribute(new Attribute("name", inpath));
                                                    document = new Document(cardlist);
                                                    document.setRootElement(cardlist);

                        BufferedReader br = new BufferedReader(new FileReader(file));
                        for(String rLine = br.readLine(); rLine!=null; rLine = br.readLine()){
                            int endIndex = rLine.lastIndexOf('-');
                            String englishVersion = null;
                            String russianVersion = null;
                            if (endIndex != -1) {
                                englishVersion = rLine.substring(0, endIndex);
                                russianVersion = rLine.substring(endIndex + 1, rLine.length());
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

                                new FileOutputStream("e:\\words\\" + cutFileName + "_toLearnByHeart.mcl"), "cp1252"));
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
}
