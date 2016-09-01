package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Roleauthority;
import com.p8499.lang.mask.RoleauthorityMask;

@Component("roleauthorityListener")
public class RoleauthorityListener extends BeanListener<Roleauthority,RoleauthorityMask,Integer>
{	@Override
	public void afterUpdate(Roleauthority bean)
	{	RoleauthorityMask mask=new RoleauthorityMask();
		mask.setRaid(true).setRarlid(true).setRaauid(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer raid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Roleauthority",raid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Roleauthority bean)
	{	RoleauthorityMask mask=new RoleauthorityMask();
		mask.setRaid(true).setRarlid(true).setRaauid(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer raid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Roleauthority",raid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
