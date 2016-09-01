package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Word;
import com.p8499.lang.mask.WordMask;

@Component("wordListener")
public class WordListener extends BeanListener<Word,WordMask,Integer>
{	@Override
	public void afterUpdate(Word bean)
	{	WordMask mask=new WordMask();
		mask.setWoid(true).setWolsid(true).setWoct(true).setWopt(true).setWocl(true).setWost(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer woid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Word",woid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Word bean)
	{	WordMask mask=new WordMask();
		mask.setWoid(true).setWolsid(true).setWoct(true).setWopt(true).setWocl(true).setWost(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer woid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Word",woid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
