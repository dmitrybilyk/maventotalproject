package com.learn.bases.httpheaders.nativejava;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
 
public class ResponseHeaderUtil {
 
  public static void main(String[] args) {
 
    try {
 
	URL obj = new URL("http://mkyong.com");
	URLConnection conn = obj.openConnection();
	Map<String, List<String>> map = conn.getHeaderFields();
 
	System.out.println("Printing Response Header...\n");
 
	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
		System.out.println("Key : " + entry.getKey()
											+ " ,Value : " + entry.getValue());
	}
 
	System.out.println("\nGet Response Header By Key ...\n");
	String server = conn.getHeaderField("Server");
 
	if (server == null) {
		System.out.println("Key 'Server' is not found!");
	} else {
		System.out.println("Server - " + server);
	}
 
	System.out.println("\n Done");
 
    } catch (Exception e) {
	e.printStackTrace();
    }
 
  }
}