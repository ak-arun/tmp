package com.ak.javamicroweb.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AccessLogGen {
	
	private static Integer[] STATUS = {200,200,200,300,400,500};
	private static String METHOD[]= {"GET","POST"};
	
	private static String COUNTRIES[]= {"China","China","China", "India","India","India", "United States","United States","United States", "Indonesia", "Brazil", "Nigeria", "Japan", "Russia", "Bangladesh", "Mexico"};

	private static final String[] PRODUCTS = {"Touchzimex.html", "Strongwarm.html", "ZunIty.html", "StanQuofan.html", "GeoAir.html", "Findox.html", "Sumstring.html", "Techlab.html", "Zathdom.html", "Y-fix.html", "Airron.html", "Betatough.html", "GoldenCore.html", "UnoTanfan.html", "Alphain.html", "Medphase.html", "ZonSonstock.html", "UnoEco.html", "Blackeco.html", "Cansoft.html", "IndaxBeta.html", "Ventolight.html", "Alphatex.html", "GeoString.html", "Ontolex.html"};
	
	private static final String [] REFERER = {"fectrove_fictitious.com", "ainctork_fictitious.com", "micetima_fictitious.com", "kievialk_fictitious.com", "smotanic_fictitious.com", "natomajj_fictitious.com", "golocaud_fictitious.com", "sporysea_fictitious.com", "dreengaw_fictitious.com", "berotsil_fictitious.com", "example.com"};
	
	private static final String UA[] = {"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36","Mozilla/5.0 (Linux; U; Android 2.3.5; en-us; HTC Vision Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1","Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25","Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201","Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0","Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))"};
	
	
	public static List<String> getLogs(String user, int count){
		List<String> logList = new ArrayList<String>();
		String ip;
		logList.add(getIP()+" - "+user.toLowerCase()+" "+getDate()+" "+getRequest()+" "+getRandomInt(500,511)+" "+getBytes()+" "+getReferer()+" "+getUserAgent());
		for(int index =0;index<count-1;index++) {
			ip=getIP();
			logList.add(ip+" - "+getUser(ip)+" "+getDate()+" "+getRequest()+" "+getStatusCode()+" "+getBytes()+" "+getReferer()+" "+getUserAgent());
		}
		return logList;
	}
	

	
	private static int getRandomInt(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
	
	private static String getIP() {
		return getRandomInt(1,253)+"."+getRandomInt(1,253)+"."+getRandomInt(1,253)+"."+getRandomInt(1,253);
	}
	
	private static String getUser(String ip) {
		String user="";
		for(String s : ip.split("\\.")){
			user = user+getAlp(s);
		}
		return user;
	}
	
	private static String getAlp(String s) {
		return String.valueOf((char)(((Integer.parseInt(s)%26)+1) + 64)).toLowerCase();
	}
	
	private static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z"); 
		return "["+formatter.format(new Date())+"]";
	}
	
	private static String getRequest() {
		return "\""+getRandomFromArray(METHOD)+" /"+getRandomFromArray(PRODUCTS).toString().toLowerCase()+ " HTTP/1.0\"";
	}
	
	private static Object getRandomFromArray(Object[] x) {
		return x[getRandomInt(1, x.length)-1];
	}
	
	private static String getStatusCode() {
		
		Integer stat = Integer.valueOf(getRandomFromArray(STATUS)+"");
		if(stat==400) {
			stat = getRandomInt(400, 417);
		}else if (stat==500) {
			stat = getRandomInt(500, 511);
		}
		return ""+stat;
	}
	
	private static String getBytes() {
		return ""+getRandomInt(0, 4096);
	}
	
	private static String getReferer() {
		return "\""+getRandomFromArray(REFERER)+"\"";
	}
	
	private static String getUserAgent() {
		return "\""+getRandomFromArray(UA)+"\"";
	}
	
	
	public static void main(String[] args) {
		String ip=getIP();
		System.out.println(ip);
		System.out.println(getCountryFromIp(ip));
		
		
	}
	
	
	private static String getCountryFromIp(String ip) {
		return COUNTRIES[Integer.valueOf(ip.split("\\.")[0])%(COUNTRIES.length)];
	}
	
	
}
