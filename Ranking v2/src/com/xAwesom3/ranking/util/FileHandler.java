package com.xAwesom3.ranking.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.Human;
import com.xAwesom3.ranking.UI.View;

public class FileHandler {

	private static String					path		= ("./files/");

	private static Map<String, XMLHandler>	xmlFiles	= new HashMap<String, XMLHandler>();

	public static void loadFiles() {
		xLogger.log("Loading index...");
		XMLHandler xmlIndex = new XMLHandler("index");
		xmlIndex.loadFromDropBox("index");
		xmlFiles.put("index", xmlIndex);
		xLogger.log("Finished loading index!");

		xLogger.log("Loading names...");
		XMLHandler xmlNames = new XMLHandler("names");
		xmlNames.loadFromDropBox("names");
		xmlFiles.put("names", xmlNames);
		xLogger.log("Finished loading names!");

		xLogger.log("Loading questions...");
		XMLHandler xmlQuestions = new XMLHandler("questions");
		xmlQuestions.loadFromDropBox("questions");
		xmlFiles.put("questions", xmlQuestions);
		xLogger.log("Finished loading questions!");

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
		xLogger.log("Finished processing names!");
	}

	public static XMLHandler getXMLFile(String name) {
		return xmlFiles.get(name);
	}

	public static String getPath() {
		return path;
	}

	public static void saveProgress(List<View> views, boolean finished) {
		String name = Human.getUser().getName();

		XMLHandler personalHandler = new XMLHandler(name);
		personalHandler.create();
		personalHandler.addElementList(views.get(0).getResults(personalHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler averagePupilHandler = new XMLHandler("personal");
		averagePupilHandler.create();
		averagePupilHandler.addElementList(views.get(1).getResults(averagePupilHandler));
		averagePupilHandler.addElementList(views.get(2).getResults(averagePupilHandler));
		averagePupilHandler.addElementList(views.get(3).getResults(averagePupilHandler));
		averagePupilHandler.addElementList(views.get(4).getResults(averagePupilHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler questionHandler = new XMLHandler("question");
		questionHandler.create();
		questionHandler.addElementList(views.get(5).getResults(questionHandler));
		questionHandler.addElementList(views.get(6).getResults(questionHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler keyWordHandler = new XMLHandler("keyWords");
		keyWordHandler.addElementList(views.get(7).getResults(keyWordHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler indexHandler = xmlFiles.get("index");
		String state = "finished";
		if (!finished)
			state = "process";
		indexHandler.addElement(indexHandler.createElement(state, name));

		xLogger.log("Finished saving index File");

		personalHandler.saveToDropBox(name + "/personal");
		averagePupilHandler.saveToDropBox(name + "/average");
		questionHandler.saveToDropBox(name + "/question");
		keyWordHandler.saveToDropBox(name + "/keyWord");
		indexHandler.saveToDropBox("index");

		xLogger.log("Finished saving xmlFiles");
	}
}
