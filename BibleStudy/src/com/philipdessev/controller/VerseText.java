package com.philipdessev.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.philipdessev.model.Verse;

@Controller
public class VerseText {

	@RequestMapping(value="/getVerses", method=RequestMethod.GET)
	public @ResponseBody String getVerses(
			 @RequestParam(value = "book", required = false)  String book,
			 @RequestParam(value = "chapter", required = false)  String chapter,
			 @RequestParam(value = "number", required = false)  String number,
			 @RequestParam(value = "seq", required = false)  String seq,
			 @RequestParam(value = "list", required = false)  String list
			) throws JSONException{
		
				
		List<Verse> verses = new ArrayList<Verse>();
		String[] verseSequence;
		int start = 0;
		int stop = 0;
		int chapternum = 0;
		int versenum = 0;
		
		try{
		
			if(number != "" && seq == "" && list == "" ) verses.add(new Verse(book, Integer.parseInt(chapter), Integer.parseInt(number)));
			
			if(number == "" && seq == "all" && list == "" ) verses.add(new Verse(book, Integer.parseInt(chapter), Integer.parseInt("1")));
		
			if(number == "" && seq != "" && list == "" ){
				verseSequence = seq.split("-");
				if(verseSequence.length == 2){
					start = Integer.parseInt(verseSequence[0]);
					stop = Integer.parseInt(verseSequence[1]);
				
					for(int i = start; i<= stop; i++){
						verses.add(new Verse(book,Integer.parseInt(chapter),i));
					}
				}
			}
			
			if(number == "" && seq == "" && list != "" ){
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
			Verse.setContent(verses);
		}
		
		System.out.println(JSONObject.valueToString(verses));
		return JSONObject.valueToString(verses);
	}
	
}
