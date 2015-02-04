package com.angel.queen.test;

import java.util.Date;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.angel.framework.util.DateUtil;
import com.angel.framework.util.JsonUtils;
import com.angel.framework.util.ResourceConfigUtils;
import com.angel.queen.model.ConUsers;

/**
 * 工具方法测试类
 * @author JBH
 * @version 2015-01-30
 * @since JDK1.6
 */
public class UtilTest {

	private static Logger logger = Logger.getLogger(UtilTest.class);
	
	@Test
	public void testProperties(){
		
		logger.info("------------------test----------------");
		
		String url = ResourceConfigUtils.getProConfig("test.url");
		
		Assert.assertEquals("welcom", url);
	}
	
	@Test
	public void testJson(){
		
		ConUsers user = new ConUsers();
		user.setUserName("admin");
		user.setPassword("123456");
		
		String json1 = JsonUtils.object2json(user);
		String json2 = JsonUtils.bean2json(user);
		
		System.out.println(json1);
		System.out.println(json2);
		
		System.out.println(json1.equals(json2));
	}
	
	@Test
	public void testDate(){
		
		String date = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		System.out.println(date);
	}
}
