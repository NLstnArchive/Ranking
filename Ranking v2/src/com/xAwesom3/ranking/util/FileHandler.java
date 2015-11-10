package com.xAwesom3.ranking.util;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.Pupil;

public class FileHandler {

	private static Map<String, XMLHandler> files = new HashMap<String, XMLHandler>();

	private FileHandler() {
	}

	public static void loadFiles() {
		XMLHandler names = new XMLHandler("names");
		names.loadFromDropbox("names");
		files.put("names", names);

		NodeList list = names.getNodeList("schuler");
		for (int i = 0; i < list.getLength(); i++) {
			Element e = (Element) list.item(i);
			Pupil.add(new Pupil(e.getAttribute("name"), e.getAttribute("password")));
		}

		XMLHandler questions = new XMLHandler("questions");
		questions.loadFromDropbox("questions");
		files.put("questions", questions);
	}

	public static XMLHandler getFile(String name) {
		return files.get(name);
	}
}
