package com.xAwesom3.ranking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class DropBoxHandler {

	private static DbxClient	client;
	public static String		directory	= "/ranking/bcgk/";

	static {
		DbxRequestConfig config = new DbxRequestConfig("xAwesome", Locale.getDefault().toString());
		client = new DbxClient(config, "C5PkaBanyzUAAAAAAAAB6F-2RpM3FJc6_4DFWJXrE5PrRFm1sTvzt3c30jkI4Q-Z");
	}

	public static void save(String target, String source) {
		try {
			String localPath = XMLHandler.getPath() + source + ".xml";
			File f = new File(localPath);
			FileInputStream stream = new FileInputStream(f);
			client.uploadFile(directory + target + ".xml", DbxWriteMode.force(), f.length(), stream);
			stream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File load(String target, String source) {
		File f = new File(XMLHandler.getPath() + target + ".xml");
		try {
			f.createNewFile();
			FileOutputStream stream = new FileOutputStream(f);
			client.getFile(directory + source + ".xml", null, stream);
			stream.close();
			System.out.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}