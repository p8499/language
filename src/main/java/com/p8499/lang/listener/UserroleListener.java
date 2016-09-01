package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Userrole;
import com.p8499.lang.mask.UserroleMask;

@Component("userroleListener")
public class UserroleListener extends BeanListener<Userrole,UserroleMask,Integer>
{	@Override
	public void afterUpdate(Userrole bean)
	{	UserroleMask mask=new UserroleMask();
		mask.setUrid(true).setUrusid(true).setUrrlid(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer urid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Userrole",urid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Userrole bean)
	{	UserroleMask mask=new UserroleMask();
		mask.setUrid(true).setUrusid(true).setUrrlid(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer urid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Userrole",urid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
