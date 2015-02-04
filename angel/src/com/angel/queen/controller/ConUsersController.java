package com.angel.queen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angel.queen.common.BaseController;
import com.angel.queen.model.ConUsers;
import com.angel.queen.service.IConUsersService;

/**
 * 类名称：
 * 类描述：
 * @author JBH
 * @version 2015-01-26
 * @since JDK1.6
 */
@Controller
@RequestMapping("/index")
public class ConUsersController 
				extends BaseController{

	/** 日志记录 */
	private final Logger logger = Logger.getLogger(ConUsersController.class);
	
	/** 创建订单Service注入 */
	@Autowired
	private IConUsersService conUsersServiceImpl;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(){
		
		return "login";
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, ConUsers loginUser){
		
		logger.info("-----------------用户登录方法---------------");
		
		ConUsers user = this.conUsersServiceImpl.login(loginUser);
		if(user != null){
			
			//如果需要重定向，让浏览器显示的URL地址栏发生改变，那么，这一步取消
			//参数赋值以及传递
			//model.addAttribute("msg", "login success");
			logger.info("-------------登录成功-----------");
			super.session.setAttribute("loginUser", user);
			return "redirect:/index";
		}else{
			model.addAttribute("msg", "login failure");
			logger.info("-------------登录失败-----------");
			return "login";
		}
	}
	
}
