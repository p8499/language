package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Segment;
import com.p8499.lang.mask.SegmentMask;

@Component("segmentListener")
public class SegmentListener extends BeanListener<Segment,SegmentMask,Integer>
{	@Override
	public void afterUpdate(Segment bean)
	{	SegmentMask mask=new SegmentMask();
		mask.setTrasid(true).setTrpi(true).setTrhz(true).setTrst(true).setTrusid(true).setTrupdd(true).setTrupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer trasid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Segment",trasid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Segment bean)
	{	SegmentMask mask=new SegmentMask();
		mask.setTrasid(true).setTrpi(true).setTrhz(true).setTrst(true).setTrusid(true).setTrupdd(true).setTrupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer trasid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Segment",trasid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
