package in.scholarreport.struts2.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigParser {
	static Logger logger = Logger.getLogger(ConfigParser.class);

	public static void startParsing() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Map configMap = SRConfiguration.getInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("srconfig.xml"));
			NodeList nodes = document.getElementsByTagName("entry");
			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node nNode = nodes.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					configMap.put(getValue("entryname", element),
							getValue("entryvalue", element));
				}
			}
		} catch (SAXException e) {
			logger.error("Error while paring srconfig : " + e.getMessage());
		} catch (IOException e) {
			logger.error("Error while reading srconfig : " + e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error("Error while paring srconfig : " + e.getMessage());
		}
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

}
