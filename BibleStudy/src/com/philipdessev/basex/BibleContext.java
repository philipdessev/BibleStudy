package com.philipdessev.basex;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQException;

import org.w3c.dom.Node;

// this method should be aware of the bible version

public class BibleContext {

	public static String getText(String osisid) {

		String SelectedBible = "BGVOSIS";
		Node node;
		List<String> result = new ArrayList<>();
		String query =

		"for $x in doc(\"" + SelectedBible + "\")//verse[@osisID=\"" + osisid
				+ "\"]" + "return $x";

		System.out.println(query);

		XMLDataProvider provider = new XMLDataProvider();

		try {
			result = provider.getStringfomXQuery(query);
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.get(0);
		// return "The node content";

	}

	public static List<String> getVerseSeq(String osisidStart, String osisidend) {
		String SelectedBible = "BGVOSIS";
		String query =

		"for $x in doc(\"" + SelectedBible + "\")//verse"
				+ " where $x/@osisID >= \"" + osisidStart
				+ "\" and  $x/@osisID <= \"" + osisidend + "\"" + " return $x";

		List<String> resultList = new ArrayList<>();
		try {
			resultList = new XMLDataProvider().getStringfomXQuery(query);
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	public static List<String> getVerseList(String[] verseList) {
		String SelectedBible = "BGVOSIS";
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < verseList.length; i++) {
			buffer.append("\"");
			buffer.append(verseList[i]);
			buffer.append("\"");
			if (i < verseList.length - 1) {
				buffer.append(",");
			}
		}

		String query = "for $x in doc(\"" + SelectedBible + "\")//verse "
				+ "where $x/@osisID = (" + buffer.toString() + ") return $x";

		List<String> resultList = new ArrayList<>();
		try {
			resultList = new XMLDataProvider().getStringfomXQuery(query);
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	public static List<String> getVerseChapter(String osisID) {
		String SelectedBible = "BGVOSIS";
		StringBuffer buffer = new StringBuffer();

		String query =

		"for $x in doc(\"" + SelectedBible + "\")//verse"
				+ " where contains($x/@osisID , \"" + osisID +  "\") return $x";

		List<String> resultList = new ArrayList<>();
		try {
			resultList = new XMLDataProvider().getStringfomXQuery(query);
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

}
