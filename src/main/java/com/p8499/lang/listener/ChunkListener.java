package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Chunk;
import com.p8499.lang.mask.ChunkMask;

@Component("chunkListener")
public class ChunkListener extends BeanListener<Chunk,ChunkMask,Integer>
{	@Override
	public void afterUpdate(Chunk bean)
	{	ChunkMask mask=new ChunkMask();
		mask.setCpid(true).setCplsid(true).setCpsi(true).setCptg(true).setCpft(true).setCpsort(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer cpid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Chunk",cpid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Chunk bean)
	{	ChunkMask mask=new ChunkMask();
		mask.setCpid(true).setCplsid(true).setCpsi(true).setCptg(true).setCpft(true).setCpsort(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer cpid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Chunk",cpid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
