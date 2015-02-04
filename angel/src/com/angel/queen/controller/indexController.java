package com.angel.queen.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 类名称：
 * 类描述：
 * @author JBH
 * @version 2015-01-26
 * @since JDK1.6
 */
@Controller
public class IndexController {

	/** 日志记录 */
	private final Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping(value = {"/index", "/"})
	public String index(){
		
		logger.info("-----------------首页--------------");
		return "index";
	}
}
