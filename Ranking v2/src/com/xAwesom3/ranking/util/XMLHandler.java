package com.xAwesom3.ranking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLHandler {

	private Document	doc;
	private String		name;
	private File		f;

	private Element		rootElement;

	public XMLHandler(String name) {
		this.name = name;
		f = new File(FileHandler.getPath() + name + ".xml");
		try {
			f.createNewFile();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		f.deleteOnExit();
	}

	public void load() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(f));
		}
		catch (SAXException | IOException | ParserConfigurationException e) {
			xLogger.log("Failed to load XML file " + f.getPath() + ", cause: " + e.getMessage());
			return;
		}
		rootElement = (Element) doc.getElementsByTagName("root").item(0);
	}

	public void loadFromDropBox(String dbPath) {
		DropBoxHandler.load(name, dbPath);
		load();
	}

	public void create() {
		try {
			f.createNewFile();
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		}
		catch (Exception e) {
			xLogger.log("Failed to create empty XML file " + f.getPath() + ", cause: " + e.getMessage());
		}
		System.out.println(toValidString(name));
		rootElement = doc.createElement(toValidString(name));
		doc.appendChild(rootElement);
	}

	public void save() {
		try {
			Transformer t = null;
			t = TransformerFactory.newInstance().newTransformer();
			if (!f.exists())
				f.createNewFile();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			t.transform(source, result);
		}
		catch (Exception e) {
			xLogger.log("Failed to save File " + f.getPath() + "cause: " + e.getMessage());
		}
	}

	public void addElement(Element element) {
		if (element == null)
			return;
		if (rootElement != null)
			rootElement.appendChild((Node) element);
		else
			doc.appendChild((Node) element);
	}

	public void addElementList(List<Element> list) {
		if (list == null)
			return;

		for (Element element : list) {
			addElement(element);
		}
	}

	public Element createElement(String name, String textContent) {
		Element e = doc.createElement(toValidString(name));
		e.setTextContent(textContent);
		return e;
	}

	public String getUniqueElementText(String name) {
		Element result = (Element) doc.getElementsByTagName(name).item(0);
		if (result != null)
			return result.getTextContent();
		else
			return "";
	}

	public void saveToDropBox(String dbPath) {
		save();
		DropBoxHandler.save(dbPath, name);
	}

	public NodeList getNodeList(String tag) {
		return doc.getElementsByTagName(tag);
	}

	public Element getRootElement(int index) {
		return (Element) doc.getChildNodes().item(index);
	}

	private String toValidString(String string) {
		String newString = string;
		if (string != null)
			newString = string.replace(' ', '_');
		xLogger.log("Converted " + string + " to " + newString);
		return newString;
	}

	public boolean existInCloud() {
		boolean value = DropBoxHandler.fileExists(name);
		xLogger.log(name + " does exists: " + value);
		return value;
	}
}
