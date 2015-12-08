package com.xAwesom3.ranking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import com.xAwesom3.ranking.Human;

public class DropBoxHandler {

	private static DbxClient	client;

	private static String		dbPath	= "/ranking/bcgk/";

	static {
		DbxRequestConfig config = new DbxRequestConfig("xAwesome", Locale.getDefault().toString());
		client = new DbxClient(config, "C5PkaBanyzUAAAAAAAAB6F-2RpM3FJc6_4DFWJXrE5PrRFm1sTvzt3c30jkI4Q-Z");
		xLogger.log("Staticly initialised");
	}

	public static void save(String target, String source) {
		try {
			String localPath = FileHandler.getPath() + source + ".xml";
			File f = new File(localPath);
			FileInputStream stream = new FileInputStream(f);
			client.uploadFile(dbPath + target + ".xml", DbxWriteMode.force(), f.length(), stream);
			stream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			xLogger.log("Failed to save file " + source + " to DropBox: " + e.getMessage());
		}
		xLogger.log("Finished saving file " + source + ".xml to " + target + ".xml");
	}

	public static File load(String target, String source) {
		File f = new File(FileHandler.getPath() + target + ".xml");
		try {
			f.createNewFile();
			FileOutputStream stream = new FileOutputStream(f);
			client.getFile(dbPath + source + ".xml", null, stream);
			stream.close();
		}
		catch (Exception e) {
			xLogger.log("Failed to load file " + dbPath + source + " from DropBox: " + e.getMessage());
		}
		xLogger.log("Finished loading file " + dbPath + source + ".xml to " + target + ".xml");
		return f;
	}

	public static DbxEntry.WithChildren listFiles() {
		xLogger.log("listing files from: " + dbPath + Human.getUser().getName());
		try {
			return client.getMetadataWithChildren(dbPath + Human.getUser().getName());
		}
		catch (DbxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean fileExists(String name) {
		name = name + ".xml";
		DbxEntry.WithChildren list = listFiles();
		for (DbxEntry child : list.children) {
			xLogger.log("comparing file " + name + " with " + child.name);
			if (child.name.equalsIgnoreCase(name)) {
				xLogger.log("Found file: " + name + " in " + child);
				return true;
			}
		}
		return false;
	}
}
