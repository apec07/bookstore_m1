package idv.utli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.naming.Context;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXmlDomParser {
	
	public  Logger LOGGER = LogManager.getLogger(this.getClass());
	private InputStream is;
	
	public ReadXmlDomParser (InputStream is) {
		this.is = is;
	}
	
	
	public String getRefName() {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			NodeList list = doc.getElementsByTagName("res-ref-name");
			for(int i=0;i<list.getLength();i++) {
		         Node node = list.item(i);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;

	                  // get staff's attribute
	                  String id = element.getAttribute("id");

	                  // get text
	                  String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
	                  String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
	                  String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

	                  NodeList salaryNodeList = element.getElementsByTagName("salary");
	                  String salary = salaryNodeList.item(0).getTextContent();

	                  // get salary's attribute
	                  String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

	                  System.out.println("Current Element :" + node.getNodeName());
	                  System.out.println("Staff Id : " + id);
	                  System.out.println("First Name : " + firstname);
	                  System.out.println("Last Name : " + lastname);
	                  System.out.println("Nick Name : " + nickname);
	                  System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

	              }
			}
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
