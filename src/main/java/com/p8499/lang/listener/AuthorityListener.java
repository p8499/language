package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Authority;
import com.p8499.lang.mask.AuthorityMask;

@Component("authorityListener")
public class AuthorityListener extends BeanListener<Authority,AuthorityMask,String>
{	@Override
	public void afterUpdate(Authority bean)
	{	AuthorityMask mask=new AuthorityMask();
		mask.setAuid(true).setAugrp(true).setAuname(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(String auid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Authority",auid,ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Authority bean)
	{	AuthorityMask mask=new AuthorityMask();
		mask.setAuid(true).setAugrp(true).setAuname(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(String auid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Authority",auid,ACTION_BEFOREDELETE);
		}
	}
}
