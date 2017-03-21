package com.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vo.PatternAndMatcher;

public class ParseURLs {

	public static void main(String[] args) throws Exception {

		List<String> urlList = new ArrayList<String>();
		urlList.add("http://apexconsultingsolutions.com/contact-us/");
//		urlList.add("http://www.onepointinfotech.com/");		
		Set<String> number = getHTML(urlList);		
	}

	public static Set<String> getHTML(List<String> urlList) throws Exception {
		Set<String> number = new HashSet<String>();
		
		//create regex pattern
		String htmlTagPattern = "(?i)<a([^>]+)>(.+?)</a>";
		String htmlHrefTagPattern = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
		String numberPatter = "([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4})";
		
		PatternAndMatcher patternAndMatcher = new PatternAndMatcher(htmlTagPattern, htmlHrefTagPattern, numberPatter); 
		for (int i = 0; i < urlList.size(); i++) {			
			try{
				//get url from List
				String temp = urlList.get(i);
				
				//make connection to url
				BufferedReader rd = connectionToUrl(temp);
	
				//extract phone number and link from url
				extraxtNumberAndUrl(urlList, number, rd, patternAndMatcher);
			}catch(Exception e){			
			}
		}		
		return number;
	}

	//Parse the content on the page and stores any phone number
	//Add any parsed out links found on the page to ArralList
	private static void extraxtNumberAndUrl(List<String> urlList, Set<String> number, BufferedReader rd, PatternAndMatcher patternAndMatcher)
			throws IOException {
		String str;
		Pattern checkRegex = patternAndMatcher.getCheckRegex();
		Pattern pTag = patternAndMatcher.getpTag();
		Pattern pLink = patternAndMatcher.getpLink();
		Matcher mTag, mLink;
		while ((str = rd.readLine()) != null) {
			
			//Extract all phone number
			
			Matcher regexMatcher = checkRegex.matcher(str);
			while (regexMatcher.find()) {
				if (regexMatcher.group().length() != 0) {							
					if(number.add(regexMatcher.group().trim()))
						System.out.println(regexMatcher.group().trim());
				}
			}
			
			//Extract all URl and add it to List.			
			mTag = pTag.matcher(str);
			while (mTag.find()) {
				String href = mTag.group(1); // get the values of href
				mLink = pLink.matcher(href);
				while (mLink.find()) {
					String link = mLink.group(1).trim();
					if (link.length() > 7){
						link = link.replaceAll("\"", "");
						link = link.replaceAll("\'", "");
						if(!urlList.contains(link)){									
							urlList.add(link);									
						}
					}
				}
			}

		}
		rd.close();
	}

	//Request Get to retrive the content of page
	private static BufferedReader connectionToUrl(String temp)
			throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(temp);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		return rd;
	}
}
