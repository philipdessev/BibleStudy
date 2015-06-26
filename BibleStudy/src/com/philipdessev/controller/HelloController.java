package com.philipdessev.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.dom.DOMSource;
import com.philipdessev.basex.*;
import org.w3c.dom.Node;


@Controller
public class HelloController {

	@RequestMapping({"/welcome", "/Welcome", "/WELCOME"})
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response , Model model) throws Exception {
		
		ModelAndView modelandview = new ModelAndView("HelloPage");
		modelandview.addObject("welcomeMessage", "Hi There");
		
		return modelandview;
	}
	
	@RequestMapping("/BBST")
	protected String BBSTXMLController(HttpServletRequest request,
			HttpServletResponse response , Model model) throws Exception {
		
		Node node;
		System.out.println( "Local Name: " + request.getServerName() +  request.getServletPath()); 
		System.out.println(  request.getLocalAddr());
   
		ModelAndView modelandview = new ModelAndView("HelloPage");
		modelandview.addObject("welcomeMessage", "Hi There");
		
		DOMSource domSource = new DOMSource();
		String query = "for $x in doc('BibleStudy') return $x";
		
		
		XMLDataProvider dp = new XMLDataProvider();
		try{
			node = dp.getNodefomXQuery(query);
			domSource.setNode(node);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		
		//System.out.println(node.getTextContent());
		model.addAttribute("obj",domSource );
		return "BBST";
	}

	
	
	
}
