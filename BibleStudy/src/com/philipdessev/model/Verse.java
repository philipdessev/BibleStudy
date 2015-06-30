package com.philipdessev.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.websocket.Session;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.xquery.XQException;

import org.springframework.core.io.Resource;
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
	private ServletContext context;
	
	public Verse() {
	
	}
		

	public void initVerseFromXMLString(String xml) throws ParserConfigurationException, SAXException, IOException {

		List<Node> nodelist = new ArrayList<Node>();
		XMLDataProvider provider = new XMLDataProvider();
		// <verse osisID="Matt.22.1">И Иисус пак започна да им говори с притчи, като казваше:</verse>
		// <verse osisID="Matt.22.1">И Иисус пак започна да им говори с притчи, като казваше:</verse>
		// <verse osisID="">И Иисус пак започна да им говори с притчи, като казваше:</verse>
		
		String html = verseTransform(xml);
		
		List<String> xmlList = new ArrayList<String>();
		xmlList.add(xml);
		nodelist = provider.getNodefomXMLList(xmlList);
		Node node = nodelist.get(0);
		NamedNodeMap attributesMap = node.getAttributes();
		
		String osisid = attributesMap.getNamedItem("osisID").getNodeValue();
		//String text = node.getTextContent();
		String[] params = osisid.split("\\.");
		try {
			this.setBook(params[0]);
			this.setChapter(params[1]);
			this.setNumber(params[2]);
			this.setOSISID(osisid);
			this.setText(html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(node.getNodeName());
		//System.out.println(node.getNodeValue());
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
	
	
	public String verseTransform(String verseXML){
		
		String XSLFilename = "verses.xsl";
		XSLFilename = "XML" + File.separatorChar +  "verses.xsl";

		String result = verseXML;
		String classPath = this.getClass().getClassLoader().getResource("").getPath();
		StringBuffer buffer = new StringBuffer();
		try {
			String[] pathElements = classPath.split("/");
			for(int i = 1; i < pathElements.length-1; i++){
				System.out.println(pathElements[i]);
				buffer.append(pathElements[i]);
				buffer.append(File.separatorChar);
			}
			
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		XSLFilename = buffer.append(XSLFilename).toString();
		System.out.println(XSLFilename);
		
		try {
		    StringReader reader = new StringReader(verseXML);
		    StringWriter writer = new StringWriter();
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(
		            new javax.xml.transform.stream.StreamSource(XSLFilename));

		    transformer.transform(
		            new javax.xml.transform.stream.StreamSource(reader), 
		            new javax.xml.transform.stream.StreamResult(writer));

		    result = writer.toString();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return result;
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


	public ServletContext getContext() {
		return context;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}

}
