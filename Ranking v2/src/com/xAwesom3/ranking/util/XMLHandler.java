package com.xAwesom3.ranking.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLHandler {

	private static String	PATH	= "./files/";

	private File			file;
	private String			name;

	private Document		doc;

	public XMLHandler(String name) {
		this.name = name;
		file = new File(PATH + name + ".xml");
	}

	public void create() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadFromDropbox(String source) {
		DropBoxHandler.load(name, source);
		load();
	}

	public void save() {
		try {
			Transformer t = null;
			t = TransformerFactory.newInstance().newTransformer();
			if (!file.exists())
				file.createNewFile();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			t.transform(source, result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Element createElement(String elementName, String textContent, String attr, String value, String attr2, String value2) {
		Element e = doc.createElement(elementName);
		if (textContent != null)
			e.setTextContent(textContent);
		if (attr != null)
			e.setAttribute(attr, value);
		if (attr2 != null)
			e.setAttribute(attr2, value2);
		return e;
	}

	public NodeList getNodeList(String tag) {
		return doc.getElementsByTagName(tag);
	}

	public Element getRootElement() {
		return (Element) doc.getChildNodes().item(0);
	}

	public static String getPath() {
		return PATH;
	}
}
