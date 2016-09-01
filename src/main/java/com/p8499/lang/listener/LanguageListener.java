package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Language;
import com.p8499.lang.mask.LanguageMask;

@Component("languageListener")
public class LanguageListener extends BeanListener<Language,LanguageMask,String>
{	@Override
	public void afterUpdate(Language bean)
	{	LanguageMask mask=new LanguageMask();
		mask.setLsid(true).setLsname(true).setLsloc(true).setLssort(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(String lsid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Language",lsid,ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Language bean)
	{	LanguageMask mask=new LanguageMask();
		mask.setLsid(true).setLsname(true).setLsloc(true).setLssort(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(String lsid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Language",lsid,ACTION_BEFOREDELETE);
		}
	}
}
