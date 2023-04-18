
package task2;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

public class XMLToJsonConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the XML file path: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the field name to retrieve: ");
        String fieldName = scanner.nextLine();
        scanner.close();

        try {
            // Step 1: Parse the XML file
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Step 2: Retrieve the field values and create JSON object
            NodeList nodeList = doc.getElementsByTagName(fieldName);
            if (nodeList.getLength() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(fieldName, element.getTextContent());
                        jsonArray.put(jsonObject);
                    }
                }
                System.out.println(jsonArray.toString());
            } else {
                System.out.println("Field not found in the XML file.");
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Error: ParserConfigurationException - " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Error: SAXException - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: IOException - " + e.getMessage());
        }
    }
}


