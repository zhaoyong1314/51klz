package com.angel.queen.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angel.queen.dao.mapper.ConUsersMapper;
import com.angel.queen.model.ConUsers;
import com.angel.queen.service.IConUsersService;

/**
 * 类名称：
 * 类描述：
 * @author JBH
 * @version 2015-01-26
 * @since JDK1.6
 */
@Service
public class ConUsersServiceImpl  implements IConUsersService{

	/** 日志记录 */
	private final Logger logger = Logger.getLogger(ConUsersServiceImpl.class);
	
	/** 对应用户数据访问层  */
	@Autowired
	private ConUsersMapper conUsersMapper;
	
	public ConUsers login(ConUsers user) {
		
		logger.info("------------------user login service-------------");
		return this.conUsersMapper.userLogin(user);
	}

}
