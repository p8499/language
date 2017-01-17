package com.p8499.lang.trigger;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.listener.SegmentvoteListener;
import com.p8499.lang.mapper.SegmentvoteMapper;
import com.p8499.lang.mask.SegmentvoteMask;
import com.p8499.mvc.Trigger;

@Component("SegmentvoteTrigger")
public class SegmentvoteTrigger extends Trigger
{	@Value(value="#{segmentvoteMapper}")
	private SegmentvoteMapper segmentvoteMapper;
	@Value(value="#{segmentvoteListener}")
	private SegmentvoteListener segmentvoteListener;
	@PostConstruct
	protected void register()
	{
		segmentvoteListener.registerBeforeAdd(this);
		segmentvoteListener.registerBeforeUpdate(this);
	}
	public boolean beforeSegmentvoteAdd(Segmentvote bean)
	{	return bean.getTvpo()==-1||bean.getTvpo()==1;
	}
	public boolean beforeSegmentvoteUpdate(Segmentvote origBean,Segmentvote newBean,SegmentvoteMask mask)
	{	return newBean.getTvpo()==-1||newBean.getTvpo()==1;
	}
	
}
