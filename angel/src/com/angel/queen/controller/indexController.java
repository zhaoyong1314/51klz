package com.angel.queen.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angel.queen.common.ArticleLevelEnum;
import com.angel.queen.dao.mapper.ArticleModelMapper;
import com.angel.queen.model.ArticleModel;
import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * 类名称：
 * 类描述：
 * @author JBH
 * @version 2015-01-26
 * @since JDK1.6
 */
@Controller
@RequestMapping("/mytest")
public class IndexController {

	@Autowired
	private ArticleModelMapper articleModelMapper;
 	
	/** 日志记录 */
	private final Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping(value = {"/index", "/"})
	public String index(){
		
		//插入文章
//		insertArticles();
		logger.info("-----------------首页--------------");
		return "index";
	}
	
	public void insertArticles(){
		for (int i = 0; i < 6; i++) {
			ArticleModel articleModel = new ArticleModel();
			articleModel.setArticletitle("[七嘴八舌] 小时候绝对干过的事，说说看你都干了几件？");
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("1.  看见路上的地砖，故意隔一格跳过");
			stringBuffer.append("2.  用镜面反射太阳光，在教室照来照去");
			stringBuffer.append("3.  在硬币上面铺张纸，然后、用铅笔在上面涂");
			stringBuffer.append("4.  吃完糖果之后，将糖纸包回原本的形状");
			stringBuffer.append("5.  把画着“小乌龟”的纸条贴在同学的背后");
			stringBuffer.append("6.  从后边拍别人的左肩，站在别人右边");
			stringBuffer.append("7.  踩别人的影子");
			stringBuffer.append("8.  下雨时打伞，总是故意地转那把伞");
			stringBuffer.append("9.  用手插入米缸的米里面~~插来~插去");
			stringBuffer.append("10.  有很多泡泡的塑料膜，把泡泡一个个按破");
			articleModel.setArticlecontent(stringBuffer.toString().getBytes());
			articleModel.setAuthor("zy");
			articleModel.setArticletype(1);
			articleModel.setLevel(ArticleLevelEnum.ARTICLE_LEVEL_HOT.getKey());
	   	 	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			articleModel.setPublishtime(dfs.format(new Date()));
			articleModel.setRemark("这是一个测试");
			articleModelMapper.insert(articleModel);
		}
	}

}
