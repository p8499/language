package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.lang.bean.Chunkcriteria;
import com.p8499.lang.mask.ChunkcriteriaMask;

@Component("chunkcriteriaListener")
public class ChunkcrieriaListener extends BeanListener<Chunkcriteria,ChunkcriteriaMask,Integer>
{	@Override
	public void afterUpdate(Chunkcriteria bean)
	{	ChunkcriteriaMask mask=new ChunkcriteriaMask();
		mask.setCcid(true).setCccpid(true).setCcsi(true).setCctk(true).setCctg(true);
		afterUpdateWithMask(bean,mask);
	}
	@Override
	public void afterDelete(Integer ccid)
	{	for(Cache cache:afterDeleteList)
		{	cache.put("Chunkcriteria",ccid.toString(),ACTION_AFTERDELETE);
		}
	}
	@Override
	public void beforeUpdate(Chunkcriteria bean)
	{	ChunkcriteriaMask mask=new ChunkcriteriaMask();
		mask.setCcid(true).setCccpid(true).setCcsi(true).setCctk(true).setCctg(true);
		beforeUpdateWithMask(bean,mask);
	}
	@Override
	public void beforeDelete(Integer ccid)
	{	for(Cache cache:beforeDeleteList)
		{	cache.put("Chunkcriteria",ccid.toString(),ACTION_BEFOREDELETE);
		}
	}
}
