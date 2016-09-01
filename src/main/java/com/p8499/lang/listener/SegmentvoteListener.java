package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.mask.SegmentvoteMask;

@Component("segmentvoteListener")
public class SegmentvoteListener extends BeanListener<Segmentvote,SegmentvoteMask,Integer>
{	@Override
	public void afterUpdate(Segmentvote bean)
	{	SegmentvoteMask mask=new SegmentvoteMask();
		mask.setTvid(true).setTvtaid(true).setTvsi(true).setTvusid(true).setTvpo(true).setTaupdd(true).setTaupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer tvid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Segmentvote",tvid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Segmentvote bean)
	{	SegmentvoteMask mask=new SegmentvoteMask();
		mask.setTvid(true).setTvtaid(true).setTvsi(true).setTvusid(true).setTvpo(true).setTaupdd(true).setTaupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer tvid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Segmentvote",tvid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
