package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Segmentflow;
import com.p8499.lang.mask.SegmentflowMask;

@Component("segmentflowListener")
public class SegmentflowListener extends BeanListener<Segmentflow,SegmentflowMask,Integer>
{	@Override
	public void afterUpdate(Segmentflow bean)
	{	SegmentflowMask mask=new SegmentflowMask();
		mask.setTaid(true).setTaasid(true).setTasi(true).setTapi(true).setTahz(true).setTast(true).setTausid(true).setTacrdd(true).setTacrdt(true).setTaupdd(true).setTaupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer taid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Segmentflow",taid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Segmentflow bean)
	{	SegmentflowMask mask=new SegmentflowMask();
		mask.setTaid(true).setTaasid(true).setTasi(true).setTapi(true).setTahz(true).setTast(true).setTausid(true).setTacrdd(true).setTacrdt(true).setTaupdd(true).setTaupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer taid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Segmentflow",taid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
