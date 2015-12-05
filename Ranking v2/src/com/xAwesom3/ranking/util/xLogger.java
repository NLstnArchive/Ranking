package com.xAwesom3.ranking.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class xLogger {

	private static boolean		debug	= true;

	private static File			f;
	private static PrintStream	writer;

	static {
		f = new File(FileHandler.getPath() + "log.log");
		if (f.exists())
			f.delete();
		try {
			f.createNewFile();
			writer = new PrintStream(f);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		if(!debug)
		System.setErr(writer);
	}

	public static void log(String log) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		String logString = "[" + timeStamp + "][" + getCallerClassName() + "] " + log;
		if (debug)
			System.out.println(logString);
		writer.println(logString);
	}

	private static String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(xLogger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
				String fileName = ste.getFileName();
				fileName = fileName.substring(0, fileName.length() - 5);
				return fileName;
			}
		}
		return null;
	}
}
