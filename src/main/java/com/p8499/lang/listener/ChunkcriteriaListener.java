package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.BeanListener;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.Chunkcriteria;
import com.p8499.lang.mask.ChunkcriteriaMask;

@Component("chunkcriteriaListener")
public class ChunkcriteriaListener extends BeanListener<Chunkcriteria,ChunkcriteriaMask>
{	@Override
	public Class<? extends Bean> getBeanClass()
	{	return Chunkcriteria.class;
	}
	@Override
	public Class<? extends Mask> getMaskClass()
	{	return ChunkcriteriaMask.class;
	}
}
