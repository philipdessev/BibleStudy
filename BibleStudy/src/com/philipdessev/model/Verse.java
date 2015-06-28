package com.philipdessev.model;

import java.io.Serializable;
import java.util.List;
import com.philipdessev.basex.*;


public class Verse implements Serializable{

	private static final long serialVersionUID = 10000L;
	private String book = "1Tim";
	private int chapter = 1;
	private int number = 1;
	private String text = "";
	private String OSISID = "";
	
	public Verse() {
		super();
	}
	
	public Verse(String book, int chapter, int number) {
		this();
		this.book = book;
		this.chapter = chapter;
		this.number = number;
		this.OSISID = book + "." + chapter + "." + number; 
	}

	public Verse(String m) {
		this("Error",0,0);
		this.text = "Error composing the verse";
	}	
	
	public static void setContent(List<Verse> verses) {
		for(Verse verse : verses){
			//verse.setText(BibleText.getText(verse.getOSISID()));
			verse.setText("set");
		}	
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOSISID() {
		return OSISID;
	}

}
