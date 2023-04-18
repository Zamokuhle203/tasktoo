
package task2;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class App { 
    public String getGreeting() {
        return "Hello World!";
    }
    public static void selection(String fieldName) {
        try {
            // Load the XML file
            File xmlFile = new File("C:/Users/g20n9632/Documents/java-app/app/src/main/resources/data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Get the list of record elements
            NodeList recordList = doc.getElementsByTagName("record");

            // Loop through each record element
            for (int i = 0; i < recordList.getLength(); i++) {
                Node recordNode = recordList.item(i);
                if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element recordElement = (Element) recordNode;
                    System.out.println("Record " + (i + 1) + ":");

                    // Print selected fields
                    NodeList fieldList = recordElement.getElementsByTagName(fieldName);
                    if (fieldList.getLength() > 0) {
                        Element fieldElement = (Element) fieldList.item(0);
                        System.out.println(fieldName + ": " + fieldElement.getTextContent());
                    } else {
                        System.out.println(fieldName + ": Not found");
                    }

                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        //new App().selection();
        String fieldName = "name";
        System.out.println("Hello World!");
        new App().selection(fieldName);
    }
}

