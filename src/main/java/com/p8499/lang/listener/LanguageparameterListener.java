package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Languageparameter;
import com.p8499.lang.mask.LanguageparameterMask;

@Component("languageparameterListener")
public class LanguageparameterListener extends BeanListener<Languageparameter,LanguageparameterMask,String>
{	@Override
	public void afterUpdate(Languageparameter bean)
	{	LanguageparameterMask mask=new LanguageparameterMask();
		mask.setLplsid(true).setLpsgng(true).setLpsgch(true).setLpsglf(true).setLpsgcw(true).setLpsgbw(true).setLpsgnb(true).setLpsgft(true).setLpsgfc(true).setLppong(true).setLppoch(true).setLppolf(true).setLppoft(true).setLppofc(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(String lplsid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Languageparameter",lplsid,ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Languageparameter bean)
	{	LanguageparameterMask mask=new LanguageparameterMask();
		mask.setLplsid(true).setLpsgng(true).setLpsgch(true).setLpsglf(true).setLpsgcw(true).setLpsgbw(true).setLpsgnb(true).setLpsgft(true).setLpsgfc(true).setLppong(true).setLppoch(true).setLppolf(true).setLppoft(true).setLppofc(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(String lplsid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Languageparameter",lplsid,ACTION_BEFOREDELETE);
		}
	}
}
