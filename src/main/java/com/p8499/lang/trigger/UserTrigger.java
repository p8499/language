package com.p8499.lang.trigger;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.p8499.lang.bean.User;
import com.p8499.lang.listener.UserListener;
import com.p8499.lang.mapper.UserMapper;
import com.p8499.lang.mask.UserMask;
import com.p8499.mvc.Trigger;

@Component("userTrigger")
public class UserTrigger extends Trigger
{	@Value(value="#{userMapper}")
	private UserMapper userMapper;
	@Value(value="#{userListener}")
	private UserListener userListener;
	@PostConstruct
	protected void register()
	{
		userListener.registerBeforeUpdate(this);
	}
	public boolean beforeUserUpdate(User origBean,User newBean,UserMask mask)
	{	if(mask.getUspn()||mask.getUsst())
				return false;
		return true;
	}
}
