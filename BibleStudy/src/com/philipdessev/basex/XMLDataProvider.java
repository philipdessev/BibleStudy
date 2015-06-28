package com.philipdessev.basex;


import java.io.IOException;
import java.io.StringReader;


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


	public Node getNodefomXQuery(String query) throws  XQException, SAXException, IOException, ParserConfigurationException {

		String xml = "";
		
		XQDataSource xqs = new BaseXXQDataSource();
		xqs.setProperty("port", "1984");
		xqs.setProperty("serverName", "localhost");
		
		
		// Change USERNAME and PASSWORD values
		XQConnection conn = xqs.getConnection("admin", "pass");

		XQPreparedExpression xqpe = conn.prepareExpression(query);
		XQResultSequence rs = xqpe.executeQuery();
	
	
		if (rs.next()){
			//System.out.println(rs.getItemAsString(null));
			xml = rs.getItem().getItemAsString(null);
		}
		
		
	
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
	
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
			System.out.println(doc.getDocumentElement().getNodeName()); 
		if(conn != null) conn.close();
		
		return doc.getDocumentElement();
	}
}
