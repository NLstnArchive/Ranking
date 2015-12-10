package com.xAwesom3.ranking.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xAwesom3.ranking.Human;
import com.xAwesom3.ranking.UI.MainFrame;
import com.xAwesom3.ranking.UI.View;
import com.xAwesom3.ranking.UI.views.AveragePupilView;
import com.xAwesom3.ranking.UI.views.AveragePupilView2;
import com.xAwesom3.ranking.UI.views.AveragePupilView3;
import com.xAwesom3.ranking.UI.views.AveragePupilView4;
import com.xAwesom3.ranking.UI.views.KeywordView;
import com.xAwesom3.ranking.UI.views.PersonalView;
import com.xAwesom3.ranking.UI.views.QuestionView;
import com.xAwesom3.xLib.threadPool.ThreadPool;

public class FileHandler {

	public static final int		NONE		= 0,
										PROGRESS = 1, FINISHED = 2;

	// private static String path = ("./files/");
	private static String		path;
	private static ThreadPool	threadPool	= new ThreadPool(6);

	static {
		Class<?> aclass = new FileHandler().getClass();
		URL url;
		String extURL;

		try {
			url = aclass.getProtectionDomain().getCodeSource().getLocation();
		}
		catch (SecurityException ex) {
			url = aclass.getResource(aclass.getSimpleName() + ".class");
		}

		extURL = url.toExternalForm();

		if (extURL.endsWith(".jar"))
			extURL = extURL.substring(0, extURL.lastIndexOf("/"));
		else {
			String suffix = "/" + (aclass.getName()).replace(".", "/") + ".class";
			extURL = extURL.replace(suffix, "");
			if (extURL.startsWith("jar:") && extURL.endsWith(".jar!"))
				extURL = extURL.substring(4, extURL.lastIndexOf("/"));
		}

		try {
			url = new URL(extURL);
		}
		catch (MalformedURLException mux) {

		}

		try {
			path = new File(url.toURI()).getPath() + "/Ranking/files/";
		}
		catch (URISyntaxException ex) {
			path = new File(url.getPath()).getPath() + "/Ranking/files/";
		}
	}

	private static Map<String, XMLHandler> xmlFiles = new HashMap<String, XMLHandler>();

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

		XMLHandler personalHandler = new XMLHandler("personal");
		personalHandler.create();
		personalHandler.addElementList(views.get(0).getResults(personalHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler averagePupilHandler = new XMLHandler("average");
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

		XMLHandler keyWordHandler = new XMLHandler("keyWord");
		keyWordHandler.create();
		keyWordHandler.addElementList(views.get(7).getResults(keyWordHandler));

		xLogger.log("Added averagePupilView results to xmlFile");

		XMLHandler indexHandler = xmlFiles.get("index");
		String state = "finished";
		if (!finished)
			state = "progress";
		indexHandler.addElement(indexHandler.createElement(state, name));

		xLogger.log("Finished saving index File");

		personalHandler.saveToDropBox(name + "/personal");
		averagePupilHandler.saveToDropBox(name + "/average");
		questionHandler.saveToDropBox(name + "/question");
		keyWordHandler.saveToDropBox(name + "/keyWord");
		indexHandler.saveToDropBox("index");

		xLogger.log("Finished saving xmlFiles");
	}

	public static int getProgress(String name) {
		XMLHandler index = xmlFiles.get("index");
		NodeList list = index.getNodeList("finished");
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).getTextContent().equalsIgnoreCase(name))
				return FINISHED;
		}
		list = index.getNodeList("progress");
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).getTextContent().equalsIgnoreCase(name))
				return PROGRESS;
		}
		return NONE;
	}

	public static void loadProgress(MainFrame frame, String name) {
		XMLHandler personalHandler = new XMLHandler("personal");
		if (personalHandler.existInCloud()) {
			personalHandler.loadFromDropBox(name + "/personal");

			PersonalView personalView = (PersonalView) frame.views.get(0);
			personalView.loadResults(personalHandler);
		}

		XMLHandler averagePupilHandler = new XMLHandler("average");

		if (averagePupilHandler.existInCloud()) {

			averagePupilHandler.loadFromDropBox(name + "/average");
			AveragePupilView averageView = (AveragePupilView) frame.views.get(1);
			averageView.loadResults(averagePupilHandler);

			AveragePupilView2 average2View = (AveragePupilView2) frame.views.get(2);
			average2View.loadResults(averagePupilHandler);

			AveragePupilView3 average3View = (AveragePupilView3) frame.views.get(3);
			average3View.loadResults(averagePupilHandler);

			AveragePupilView4 average4View = (AveragePupilView4) frame.views.get(4);
			average4View.loadResults(averagePupilHandler);
		}

		XMLHandler questionHandler = new XMLHandler("question");

		if (questionHandler.existInCloud()) {
			questionHandler.loadFromDropBox(name + "/question");

			QuestionView questionView1 = (QuestionView) frame.views.get(5);
			questionView1.loadResults(questionHandler);

			QuestionView questionView2 = (QuestionView) frame.views.get(6);
			questionView2.loadResults(questionHandler);
		}

		XMLHandler keyWordHandler = new XMLHandler("keyWord");

		if (keyWordHandler.existInCloud()) {
			keyWordHandler.loadFromDropBox(name + "/keyWord");

			KeywordView keyWordView = (KeywordView) frame.views.get(7);
			keyWordView.loadResults(keyWordHandler);
		}

	}
}
