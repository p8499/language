package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Wordvote;
import com.p8499.lang.mask.WordvoteMask;

@Component("wordvoteListener")
public class WordvoteListener extends BeanListener<Wordvote,WordvoteMask,Integer>
{	@Override
	public void afterUpdate(Wordvote bean)
	{	WordvoteMask mask=new WordvoteMask();
		mask.setWvid(true).setWvwaid(true).setWvsi(true).setWvusid(true).setWvpo(true).setWvupdd(true).setWvupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer wvid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Wordvote",wvid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Wordvote bean)
	{	WordvoteMask mask=new WordvoteMask();
		mask.setWvid(true).setWvwaid(true).setWvsi(true).setWvusid(true).setWvpo(true).setWvupdd(true).setWvupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer wvid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Wordvote",wvid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
