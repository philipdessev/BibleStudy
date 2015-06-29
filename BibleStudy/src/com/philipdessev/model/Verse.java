package com.philipdessev.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.philipdessev.basex.*;


public class Verse implements Serializable{

	private static final long serialVersionUID = 10000L;
	private String book = "";
	private String chapter = "";
	private String number = "";
	private String text = "";
	private String OSISID = "";
	
	public Verse() {
	
	}
		

	public void initVerseFromXMLString(String xml) throws ParserConfigurationException, SAXException, IOException {

		List<Node> nodelist = new ArrayList<Node>();
		XMLDataProvider provider = new XMLDataProvider();
		List<String> xmlList = new ArrayList<String>();
		xmlList.add(xml);
		nodelist = provider.getNodefomXMLList(xmlList);
		Node node = nodelist.get(0);
		NamedNodeMap attributesMap = node.getAttributes();
		
		String osisid = attributesMap.getNamedItem("osisID").getNodeValue();
		String text = node.getTextContent();
		String[] params = osisid.split("\\.");
		try {
			this.setBook(params[0]);
			this.setChapter(params[1]);
			this.setNumber(params[2]);
			this.setOSISID(osisid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(node.getNodeName());
		//System.out.println(node.getNodeValue());
		
		this.setText(text);

	}	
	
	public static void setContent(List<Verse> verses) throws XQException, SAXException, IOException, ParserConfigurationException {
		String osisid;
		String text;
		for(Verse verse : verses){
			osisid = verse.getOSISID();
			text = BibleContext.getText(osisid);
			verse.setText(text);
			//verse.setText("set");
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

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOSISID(String oSISID) {
		OSISID = oSISID;
	}

}
