package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Pronounce;
import com.p8499.lang.mask.PronounceMask;

@Component("pronounceListener")
public class PronounceListener extends BeanListener<Pronounce,PronounceMask,Integer>
{	@Override
	public void afterUpdate(Pronounce bean)
	{	PronounceMask mask=new PronounceMask();
		mask.setPnid(true).setPnlsid(true).setPnct(true).setPnpi(true).setPntn(true).setPnco(true).setPnvo(true).setPncl(true).setPnrm(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer pnid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Pronounce",pnid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Pronounce bean)
	{	PronounceMask mask=new PronounceMask();
		mask.setPnid(true).setPnlsid(true).setPnct(true).setPnpi(true).setPntn(true).setPnco(true).setPnvo(true).setPncl(true).setPnrm(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer pnid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Pronounce",pnid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
