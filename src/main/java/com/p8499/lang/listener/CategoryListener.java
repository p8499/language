package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.BeanListener;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.Category;
import com.p8499.lang.mask.CategoryMask;

@Component("categoryListener")
public class CategoryListener extends BeanListener<Category,CategoryMask>
{	@Override
	public Class<? extends Bean> getBeanClass()
	{	return Category.class;
	}
	@Override
	public Class<? extends Mask> getMaskClass()
	{	return CategoryMask.class;
	}
}
