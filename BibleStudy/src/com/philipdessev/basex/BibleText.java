package com.philipdessev.basex;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQException;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

// this method should be aware of the bible version
public class BibleText {

	public static String getText(String osisid) throws XQException, SAXException, IOException, ParserConfigurationException {
		String query = "";
		Node node = new XMLDataProvider().getNodefomXQuery(query);
		//return node.getTextContent();
		return "The node content";
	}
}
