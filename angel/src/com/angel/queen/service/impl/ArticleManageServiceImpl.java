package com.angel.queen.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.angel.framework.common.Page;
import com.angel.queen.dao.mapper.ArticleModelMapper;
import com.angel.queen.model.ArticleModel;
import com.angel.queen.model.ArticleModelReply;
import com.angel.queen.service.IArticleManageService;



@Service
public class ArticleManageServiceImpl implements IArticleManageService {
	
	/** 日志记录 */
	private final Logger logger = Logger.getLogger(ArticleManageServiceImpl.class);

	@Autowired
	ArticleModelMapper articleModelMapper;

	@Override
	public List<ArticleModelReply> queryArticleList(Page page) {
	  List<ArticleModel> articleModels= articleModelMapper.queryArticleList(page);
	  List<ArticleModelReply> articleModelReplys = new ArrayList();
	  for(ArticleModel model : articleModels ){
		  ArticleModelReply modelReply = parseArticleModel(model);
		  articleModelReplys.add(modelReply);
	  }
	  return articleModelReplys;
	}
	
	//解析帖子的条件
    private ArticleModelReply parseArticleModel(ArticleModel articleModel){
    	ArticleModelReply modelReply= new ArticleModelReply();
    	modelReply.setAuthor(articleModel.getAuthor());
    	modelReply.setLastReplyer("jack");
		modelReply.setPublishDateDescribe(articleModel.getPublishtime());
    	modelReply.setTitle(articleModel.getArticletitle());
		try {
			modelReply.setLastReplyDateDesribe(CovertToLocalDate(articleModel.getPublishtime()));
		} catch (ParseException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
         return modelReply;
    }
    
    //转换到通俗的时间
	private String CovertToLocalDate(String date) throws ParseException {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date begin = dfs.parse(date);
		java.util.Date end = dfs.parse(new Date().toString());
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		long second1 = between % 60 / 60;
		if(day1==0){
			return "今天";
		}
		else if (day1 == 1) {
			return "昨天";
		} else if (day1 == 2) {
			return "前天";
		} else if (day1 > 2 && day1 <= 7) {
              return "天前";
		} else  {
			return begin.toString();
		}
	}
}
