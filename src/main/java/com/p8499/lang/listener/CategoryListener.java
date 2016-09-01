package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Category;
import com.p8499.lang.mask.CategoryMask;

@Component("categoryListener")
public class CategoryListener extends BeanListener<Category,CategoryMask,Integer>
{	@Override
	public void afterUpdate(Category bean)
	{	CategoryMask mask=new CategoryMask();
		mask.setCgid(true).setCglsid(true).setCgsi(true).setCgpsi(true).setCgname(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer cgid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Category",cgid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Category bean)
	{	CategoryMask mask=new CategoryMask();
		mask.setCgid(true).setCglsid(true).setCgsi(true).setCgpsi(true).setCgname(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer cgid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Category",cgid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
