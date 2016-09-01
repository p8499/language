package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Article;
import com.p8499.lang.mask.ArticleMask;

@Component("articleListener")
public class ArticleListener extends BeanListener<Article,ArticleMask,Integer>
{	@Override
	public void afterUpdate(Article bean)
	{	ArticleMask mask=new ArticleMask();
		mask.setAtid(true).setAtcgid(true).setAtsi(true).setAtname(true).setAtusid(true).setAtupdd(true).setAtupdt(true).setAtcgname(true).setAtcsa(true).setAtcsb(true).setAtcsc(true).setAtcsd(true).setAtcse(true).setAtcsf(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer atid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Article",atid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Article bean)
	{	ArticleMask mask=new ArticleMask();
		mask.setAtid(true).setAtcgid(true).setAtsi(true).setAtname(true).setAtusid(true).setAtupdd(true).setAtupdt(true).setAtcgname(true).setAtcsa(true).setAtcsb(true).setAtcsc(true).setAtcsd(true).setAtcse(true).setAtcsf(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer atid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Article",atid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
