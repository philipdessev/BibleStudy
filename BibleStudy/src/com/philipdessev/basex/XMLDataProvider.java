package com.philipdessev.basex;


import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import java.util.*;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.basex.BaseXXQDataSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLDataProvider {

	
	public List<String> getStringfomXQuery(String query) throws XQException  {
		List<String> xmlList = new ArrayList<>();
		
		XQDataSource xqs = new BaseXXQDataSource();
		xqs.setProperty("port", "1984");
		xqs.setProperty("serverName", "localhost");
		
		// Change USERNAME and PASSWORD values
		XQConnection conn = xqs.getConnection("admin", "pass");

		XQPreparedExpression xqpe = conn.prepareExpression(query);
		XQResultSequence rs = xqpe.executeQuery();
	
		while(rs.next()){
			xmlList.add(rs.getItem().getItemAsString(null));
			//counter++;
		}
		if(conn != null) conn.close();
		

		return xmlList;
	}

	public List<Node> getNodefomXQuery(String query) throws XQException, ParserConfigurationException, SAXException, IOException  {
		
		List<String> xmlList = getStringfomXQuery(query); 
		return getNodefomXMLList(xmlList);
	}
	
	public List<Node> getNodefomXMLList(List<String> xmlList) throws ParserConfigurationException, SAXException, IOException {

		List<Node> nodeList = new ArrayList<>();
		Document doc;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		for (String xml : xmlList) {
			doc = builder.parse(new InputSource(new StringReader(xml)));
			nodeList.add( doc.getDocumentElement());
		}
		
		return nodeList;
	}
	
}
