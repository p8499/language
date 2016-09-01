package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Wordflow;
import com.p8499.lang.mask.WordflowMask;

@Component("wordflowListener")
public class WordflowListener extends BeanListener<Wordflow,WordflowMask,Integer>
{	@Override
	public void afterUpdate(Wordflow bean)
	{	WordflowMask mask=new WordflowMask();
		mask.setWaid(true).setWawoid(true).setWasi(true).setWapt(true).setWast(true).setWausid(true).setWacrdd(true).setWacrdt(true).setWaupdd(true).setWaupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer waid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Wordflow",waid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Wordflow bean)
	{	WordflowMask mask=new WordflowMask();
		mask.setWaid(true).setWawoid(true).setWasi(true).setWapt(true).setWast(true).setWausid(true).setWacrdd(true).setWacrdt(true).setWaupdd(true).setWaupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer waid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Wordflow",waid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
