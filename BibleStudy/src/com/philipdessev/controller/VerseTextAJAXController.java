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

import com.philipdessev.model.Verse;

@Controller
public class VerseTextAJAXController {

	@RequestMapping(value="/getVerses", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String>  getVerses(
			 @RequestParam(value = "book", required = false)  String book,
			 @RequestParam(value = "chapter", required = false)  String chapter,
			 @RequestParam(value = "number", required = false)  String number,
			 @RequestParam(value = "seq", required = false)  String seq,
			 @RequestParam(value = "list", required = false)  String list
			) throws JSONException
	{
		
				
		List<Verse> verses = new ArrayList<Verse>();
		String[] verseSequence;
		int start = 0;
		int stop = 0;
		int chapternum = 0;
		int versenum = 0;
		
		List<String> contentList = new ArrayList<>();
		
		try{
		
			if( !number.isEmpty() && seq.isEmpty() && list.isEmpty() ) verses.add(new Verse(book, Integer.parseInt(chapter), Integer.parseInt(number)));	
			if(number.isEmpty() && seq.equals("all") && list.isEmpty() ){
				
				seq = "1-999";
			}
		
			if(number.isEmpty() && !seq.isEmpty() && !seq.equals("all") && list.isEmpty() ){
				verseSequence = seq.split("-");
				if(verseSequence.length == 2){
					start = Integer.parseInt(verseSequence[0]);
					stop = Integer.parseInt(verseSequence[1]);
				
					for(int i = start; i<= stop; i++){
						verses.add(new Verse(book,Integer.parseInt(chapter),i));
					}
				}
			}
			
			if(number.isEmpty() && seq.isEmpty() && !list.isEmpty() ){
				String[] versesList = list.split(",");
				for(String verNum : versesList){
					System.out.println(verNum);
					chapternum = Integer.parseInt(chapter);
					versenum = Integer.parseInt(verNum);
					verses.add(new Verse(book ,chapternum,versenum));
				}
			}
		
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			verses.add(new Verse("Error"));
		}
		
		if(verses.size() == 0){
			verses.add(new Verse("Error"));
		}else{
			try {
				Verse.setContent(verses);
			} catch (XQException | SAXException | IOException
					| ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(JSONObject.valueToString(verses));
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-type", "text/html;charset=UTF-8");
	    return new ResponseEntity<String>(JSONObject.valueToString(verses), headers, HttpStatus.OK);
		
	
	}
	
}
