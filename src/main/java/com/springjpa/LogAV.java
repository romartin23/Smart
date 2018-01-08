package com.springjpa;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAV {
	
	
	public static PrintStream log;
	public static String synch = "";
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss SSS");
	
	public static void createFile()  {
		try { 
			log = new PrintStream(
				     new FileOutputStream("/usr/stratio/Smart/root/logAV.log", true)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void log(String logText) {
		synchronized (synch) {
			if (log == null) {
				createFile();
			}
		}
		
		String sDate = getDateTime();
		
		log.println("LOG: "+sDate+" -- "+logText);
		log.flush();
	}
	
	public static void error(String logText, Throwable throwable) {
		synchronized (synch) {
			if (log == null) {
				createFile();
			}
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		String sThrowable = sw.toString();
		String sDate = getDateTime();
		
		log.println("ERROR: "+sDate+" -- "+logText+" => "+sThrowable);
		log.flush();
	}
	
	private static String getDateTime() {
		long lDate = System.currentTimeMillis();
		Date date = new Date(lDate);
		return simpleDateFormat.format(date);
	}
}
