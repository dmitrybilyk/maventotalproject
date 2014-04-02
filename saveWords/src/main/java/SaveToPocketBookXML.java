import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 17.02.13
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class SaveToPocketBookXML {

    public void savePairToXML(String originalWord, String translatedWord) {
        String fileName = "ToLearnByHeartForPocketBook.mcl";
        String dirPath = "d:\\";
        File directory = new File(dirPath);
        Document document = null;
        Element card = null;
        Element cardlist = new Element("cardlist");
        String inpath = "MYOWN(En-Ru)";
        cardlist.setAttribute(new Attribute("name", inpath));

        String englishVersion = originalWord;
        String russianVersion = translatedWord;
        card = new Element("card");
        card.setAttribute(new Attribute("sides", "2"));
        Element side1Element = new Element("side");
        side1Element.setAttribute(new Attribute("type", "txt"));
        card.addContent(side1Element.setText(englishVersion));

        Element side2Element = new Element("side");
        side2Element.setAttribute(new Attribute("type", "txt"));

        card.addContent(side2Element.setText(russianVersion));

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getRawFormat());
        document = new Document(cardlist);
        document.setRootElement(cardlist);
        Writer out = null;
        boolean found = false;
        for (File f : directory.listFiles()) {
            if (f.getName().equals(fileName)) {
                found = true;
                break;
            }
        }

        if (!found) {
            document.getRootElement().addContent(card);
            try {
                out = new BufferedWriter(new OutputStreamWriter(

                        new FileOutputStream("d:\\" + fileName), "UTF8"));
                xmlOutput.output(document, out);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            System.out.println("File updated!");
        }else{
            try {
                SAXBuilder builder = new SAXBuilder();
                File xmlFile = new File(dirPath+fileName);
                Document documentToRewrite = null;
                try {
                    documentToRewrite = builder.build(xmlFile);
                } catch (JDOMException e) {
                    e.printStackTrace();
                }
                documentToRewrite.getRootElement().addContent(card);
                out = new BufferedWriter(new OutputStreamWriter(

                        new FileOutputStream("d:\\" + fileName), "UTF8"));
                xmlOutput.output(documentToRewrite, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }



        }
    }


    public static void main(String[] args) {
            TranslateForm translateForm = new TranslateForm();
//            SaveToPocketBookXML saveToPocketBookXML = new SaveToPocketBookXML();
//            saveToPocketBookXML.savePairToXML("", "");
    }
}

//                        Element cardlist = new Element("cardlist");
//                        String inpath = "MYOWN(En-Ru)";
//                        cardlist.setAttribute(new Attribute("name", inpath));
//                        Document document = new Document(cardlist);
//                        document.setRootElement(cardlist);
//
//                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//                        DocumentBuilder db = dbf.newDocumentBuilder();
//                        org.w3c.dom.Document doc = db.parse(file);
//                        doc.getDocumentElement().normalize();
//                        NodeList nodeLst = doc.getElementsByTagName("card");
//                        for (int s = 0; s < nodeLst.getLength(); s++){
//                            Node cardElement = nodeLst.item(s);
//
//
//                        }

