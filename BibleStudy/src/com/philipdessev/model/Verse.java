package com.philipdessev.model;

public class Verse {

	private String book;
	private int chapter;
	private int number;
	private String text;
	
	public Verse() {
		this("1Tim",1,1, "this is a text");
	}
	
	public Verse(String book, int chapter, int number, String text) {
		super();
		this.book = book;
		this.chapter = chapter;
		this.number = number;
		this.text = text;
	}
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
