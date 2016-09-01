package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.mask.SentenceMask;

@Component("sentenceListener")
public class SentenceListener extends BeanListener<Sentence,SentenceMask,Integer>
{	@Override
	public void afterUpdate(Sentence bean)
	{	SentenceMask mask=new SentenceMask();
		mask.setAsid(true).setAsatid(true).setAssi(true).setAscont(true).setAsst(true).setAsusid(true).setAsupdd(true).setAsupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer asid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Sentence",asid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Sentence bean)
	{	SentenceMask mask=new SentenceMask();
		mask.setAsid(true).setAsatid(true).setAssi(true).setAscont(true).setAsst(true).setAsusid(true).setAsupdd(true).setAsupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer asid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Sentence",asid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
