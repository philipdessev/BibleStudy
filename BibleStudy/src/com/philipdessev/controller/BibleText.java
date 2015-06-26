package com.philipdessev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.philipdessev.model.Verse;

@Controller
public class BibleText {

	@RequestMapping("/getVerses")
	public @ResponseBody List<Verse> getVerses(){
		List<Verse> verses = new ArrayList<Verse>();
		verses.add(new Verse());
		
		return verses;
	}
	
}
