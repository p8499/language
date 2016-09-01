package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.User;
import com.p8499.lang.mask.UserMask;

@Component("userListener")
public class UserListener extends BeanListener<User,UserMask,String>
{	@Override
	public void afterUpdate(User bean)
	{	UserMask mask=new UserMask();
		mask.setUsid(true).setUspswd(true).setUsname(true).setUsst(true).setUslsid(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(String usid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("User",usid,ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(User bean)
	{	UserMask mask=new UserMask();
		mask.setUsid(true).setUspswd(true).setUsname(true).setUsst(true).setUslsid(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(String usid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("User",usid,ACTION_BEFOREDELETE);
		}
	}
}
