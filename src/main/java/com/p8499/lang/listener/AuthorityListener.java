package com.p8499.lang.listener;

import org.springframework.stereotype.Component;
import com.p8499.mvc.BeanListener;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.Authority;
import com.p8499.lang.mask.AuthorityMask;

@Component("authorityListener")
public class AuthorityListener extends BeanListener<Authority,AuthorityMask>
{	@Override
	public Class<? extends Bean> getBeanClass()
	{	return Authority.class;
	}
	@Override
	public Class<? extends Mask> getMaskClass()
	{	return AuthorityMask.class;
	}
}
