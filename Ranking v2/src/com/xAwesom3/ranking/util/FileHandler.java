package com.xAwesom3.ranking.util;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.Human;

public class FileHandler {

	private static String					path		= ("./files/");

	private static Map<String, XMLHandler>	xmlFiles	= new HashMap<String, XMLHandler>();

	public static void loadFiles() {
		XMLHandler xmlIndex = new XMLHandler("index");
		xmlIndex.loadFromDropBox("index");
		xmlFiles.put("index", xmlIndex);

		XMLHandler xmlNames = new XMLHandler("names");
		xmlNames.loadFromDropBox("names");
		xmlFiles.put("names", xmlNames);

		XMLHandler xmlQuestions = new XMLHandler("questions");
		xmlQuestions.loadFromDropBox("questions");
		xmlFiles.put("questions", xmlQuestions);

		processNames();
	}

	private static void processNames() {
		NodeList list = xmlFiles.get("names").getNodeList("schuler");
		for (int i = 0; i < list.getLength(); i++) {
			Element e = (Element) list.item(i);
			Human.add(new Human(e.getTextContent(), e.getAttribute("password"), Integer.parseInt(e.getAttribute("gender"))));
		}
		NodeList list2 = xmlFiles.get("names").getNodeList("lehrer");
		for (int i = 0; i < list2.getLength(); i++) {
			Element e = (Element) list2.item(i);
			Human.add(new Human(e.getTextContent(), e.getAttribute("password"), Integer.parseInt(e.getAttribute("gender"))));
		}
	}

	public static XMLHandler getXMLFile(String name) {
		return xmlFiles.get(name);
	}

	public static String getPath() {
		return path;
	}
}
