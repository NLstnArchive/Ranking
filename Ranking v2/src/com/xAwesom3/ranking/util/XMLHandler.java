package com.xAwesom3.ranking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLHandler {

	private Document	doc;
	private String		name;
	private File		f;

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
			e.printStackTrace();
		}
	}

	public void loadFromDropBox(String dbPath) {
		DropBoxHandler.load(name, dbPath);
		load();
	}

	public void create() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
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
			// xLogger.log("Failed to save File " + location + "cause: " + e.getMessage());
		}
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
}
