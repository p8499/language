package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Tagging;
import com.p8499.lang.mask.TaggingMask;

@Component("taggingListener")
public class TaggingListener extends BeanListener<Tagging,TaggingMask,Integer>
{	@Override
	public void afterUpdate(Tagging bean)
	{	TaggingMask mask=new TaggingMask();
		mask.setTgasid(true).setTgcont(true).setTgst(true).setTgusid(true).setTgupdd(true).setTgupdt(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer tgasid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Tagging",tgasid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Tagging bean)
	{	TaggingMask mask=new TaggingMask();
		mask.setTgasid(true).setTgcont(true).setTgst(true).setTgusid(true).setTgupdd(true).setTgupdt(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer tgasid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Tagging",tgasid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
