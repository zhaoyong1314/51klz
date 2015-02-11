package com.angel.queen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.angel.queen.model.SysCmsNews;
import com.angel.queen.service.ISysCmsNewsService;

@Controller
@RequestMapping("/news")
public class SysCmsNewsController {

	private static Logger logger = Logger.getLogger(SysCmsNewsController.class);
	
	@Autowired
	private ISysCmsNewsService sysCmsNewsServiceImpl;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createNews(){
		
		return "news_create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody int createNews(SysCmsNews sysCmsNews, Model model){
		
		int result = this.sysCmsNewsServiceImpl.addNews(sysCmsNews);
		if(result == 1){
			logger.info("-----------create news success-----------------");
		}else{
			logger.info("-----------creaet news failure-----------------");
		}
		return result;
	}
}
