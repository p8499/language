package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Role;
import com.p8499.lang.mask.RoleMask;

@Component("roleListener")
public class RoleListener extends BeanListener<Role,RoleMask,String>
{	@Override
	public void afterUpdate(Role bean)
	{	RoleMask mask=new RoleMask();
		mask.setRlid(true).setRlname(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(String rlid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Role",rlid,ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Role bean)
	{	RoleMask mask=new RoleMask();
		mask.setRlid(true).setRlname(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(String rlid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Role",rlid,ACTION_BEFOREDELETE);
		}
	}
}
