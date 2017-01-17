package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.BeanListener;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.Article;
import com.p8499.lang.mask.ArticleMask;

@Component("articleListener")
public class ArticleListener extends BeanListener<Article,ArticleMask>
{	@Override
	public Class<? extends Bean> getBeanClass()
	{	return Article.class;
	}
	@Override
	public Class<? extends Mask> getMaskClass()
	{	return ArticleMask.class;
	}
}
