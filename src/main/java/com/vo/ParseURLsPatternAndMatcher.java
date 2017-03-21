package com.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseURLsPatternAndMatcher {
	private String HTML_TAG_PATTERN;
	private String HTML_HREF_TAG_PATTERN;
	private Matcher mTag, mLink;
	private Pattern pTag, pLink;
	private Pattern checkRegex;
	
	//Set all values.
	public ParseURLsPatternAndMatcher(String htmlTagPatter, String htmlHrefTagPattern, String numberPatter) {
		super();
		this.HTML_TAG_PATTERN = htmlTagPatter;
		this.HTML_HREF_TAG_PATTERN = htmlHrefTagPattern;		
		this.pTag = Pattern.compile(HTML_TAG_PATTERN); 
		this.pLink = Pattern.compile(HTML_HREF_TAG_PATTERN);
		this.checkRegex = Pattern.compile(numberPatter);
	}
	
	public String getHTML_TAG_PATTERN() {
		return HTML_TAG_PATTERN;
	}
	public void setHTML_TAG_PATTERN(String hTML_TAG_PATTERN) {
		HTML_TAG_PATTERN = hTML_TAG_PATTERN;
	}
	public String getHTML_HREF_TAG_PATTERN() {
		return HTML_HREF_TAG_PATTERN;
	}
	public void setHTML_HREF_TAG_PATTERN(String hTML_HREF_TAG_PATTERN) {
		HTML_HREF_TAG_PATTERN = hTML_HREF_TAG_PATTERN;
	}
	public Matcher getmTag() {
		return mTag;
	}
	public void setmTag(Matcher mTag) {
		this.mTag = mTag;
	}
	public Matcher getmLink() {
		return mLink;
	}
	public void setmLink(Matcher mLink) {
		this.mLink = mLink;
	}
	public Pattern getpTag() {
		return pTag;
	}
	public void setpTag(Pattern pTag) {
		this.pTag = pTag;
	}
	public Pattern getpLink() {
		return pLink;
	}
	public void setpLink(Pattern pLink) {
		this.pLink = pLink;
	}
	public Pattern getCheckRegex() {
		return checkRegex;
	}
	public void setCheckRegex(Pattern checkRegex) {
		this.checkRegex = checkRegex;
	}

}
