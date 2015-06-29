package com.philipdessev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQException;

import org.json.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.philipdessev.basex.BibleContext;
import com.philipdessev.model.Verse;

@Controller
public class VerseTextAJAXController {

	@RequestMapping(value = "/getVerses", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getVerses(
			@RequestParam(value = "book", required = false) String book,
			@RequestParam(value = "chapter", required = false) String chapter,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "seq", required = false) String seq,
			@RequestParam(value = "list", required = false) String list)
			throws JSONException {

		List<Verse> verses = new ArrayList<Verse>();
		String[] verseSequenceMargins;
		String[] versesList;
		String start = "";
		String stop = "";
		String bookChapter = book + "." + chapter;

		List<String> contentList = new ArrayList<>();

		try {

			if (!number.isEmpty() && seq.isEmpty() && list.isEmpty()){
				start = bookChapter + "." + number;
				contentList = BibleContext.getVerseSeq(start, start);
				
			}
			if (number.isEmpty() && seq.equals("all") && list.isEmpty()) {
				contentList = BibleContext.getVerseChapter(bookChapter);
			}

			if (number.isEmpty() && !seq.isEmpty() && !seq.equals("all") && list.isEmpty()) {
				verseSequenceMargins = seq.split("-");
				if (verseSequenceMargins.length == 2) {
					List<String> versesOsisIDs = new ArrayList<String>();
					int Start;
					int End;
					try {
						Start = Integer.parseInt(verseSequenceMargins[0]);
						End = Integer.parseInt(verseSequenceMargins[1]);
						for(int j = Start; j <= End; j++ ){
							versesOsisIDs.add( bookChapter + "." + j);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					versesList = versesOsisIDs.toArray(new String[versesOsisIDs.size()]); 
					contentList = BibleContext.getVerseList(versesList);
				}
			}

			if (number.isEmpty() && seq.isEmpty() && !list.isEmpty()) {
				versesList = list.split(",");
				for(int k = 0; k<versesList.length; k++){
					versesList[k] =  bookChapter + "." + versesList[k];
				}
				contentList = BibleContext.getVerseList(versesList);
				
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			verses.add(new Verse());
		}

		if (contentList.size() == 0) {
			verses.add(new Verse());
		} else {
			for(String verseXML:contentList){
				try {
					Verse verse = new Verse();
					verse.initVerseFromXMLString(verseXML);
					verses.add(verse);
				} catch (ParserConfigurationException | SAXException| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println(JSONObject.valueToString(verses));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/html;charset=UTF-8");
		return new ResponseEntity<String>(JSONObject.valueToString(verses),
				headers, HttpStatus.OK);

	}
}
