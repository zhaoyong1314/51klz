package com.angel.queen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(String username, String password){
		
		logger.info("-------------login username--------" + username);
		logger.info("-------------login password--------" + password);
		
		ConUsers exist = this.conUsersServiceImpl.findUserByUserName(username);
		if(exist != null){//用户名不存在
			
			ConUsers loginUser = new ConUsers();
			loginUser.setUserName(username);
			loginUser.setPassword(password);
			
			ConUsers user = this.conUsersServiceImpl.login(loginUser);
			if(user != null){//用户登录成功
				
				super.session.setAttribute("loginUser", user);
				return "0";
			}else{
				logger.info("----------------密码验证错误----------------");
				return "2";
			}
		}else{
			logger.info("---------------用户名不存在---------------------");
			return "1";
		}
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(){
		
		return "register";
	}
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		
		super.session.invalidate();//注销
		return "redirect:/index/login";
	}
	
	@RequestMapping("/valid")
	public @ResponseBody String validUsername(String username){
		
		logger.info("-----------valid username----------" + username);
		
		ConUsers exist = this.conUsersServiceImpl.findUserByUserName(username);
		if(exist != null){
			
			return "true";
		}else{
			return "false";
		}
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody String addUser(ConUsers user){
		
		int result =  this.conUsersServiceImpl.createUser(user);
		if(result == 1){
			return "true";
		}else{
			return "false";
		}
	}
}
