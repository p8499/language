package com.p8499.lang.trigger;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.p8499.lang.bean.Segmentflow;
import com.p8499.lang.listener.SegmentflowListener;
import com.p8499.lang.mapper.SegmentflowMapper;
import com.p8499.lang.mask.SegmentflowMask;
import com.p8499.mvc.FilterConditionExpr;
import com.p8499.mvc.FilterLogicExpr;
import com.p8499.mvc.Trigger;

@Component("SegmentflowTrigger")
public class SegmentflowTrigger extends Trigger
{	@Value(value="#{segmentflowMapper}")
	private SegmentflowMapper segmentflowMapper;
	@Value(value="#{segmentflowListener}")
	private SegmentflowListener segmentflowListener;
	@PostConstruct
	protected void register()
	{
		segmentflowListener.registerBeforeAdd(this);
		segmentflowListener.registerBeforeUpdate(this);
	}
	public boolean beforeSegmentflowAdd(Segmentflow bean)
	{	if(bean.getTast()==Segmentflow.TAST_VOTING)
			return segmentflowMapper.count(filterSegmentflow(bean.getTaasid(),Segmentflow.TAST_VOTING).toString())==0;
		return true;
	}
	public boolean beforeSegmentflowUpdate(Segmentflow origBean,Segmentflow newBean,SegmentflowMask mask)
	{	if(mask.getTaasid()||mask.getTast())
			if(newBean.getTast()==Segmentflow.TAST_VOTING)
				return segmentflowMapper.count(filterSegmentflow(newBean.getTaasid(),Segmentflow.TAST_VOTING).toString())==0;
		return true;
	}
	private static FilterLogicExpr filterSegmentflow(Integer taasid,Integer tast)
	{	FilterConditionExpr condition1=FilterConditionExpr.equalsNumber(Segmentflow.FIELD_TAASID,taasid);
		FilterConditionExpr condition2=FilterConditionExpr.equalsNumber(Segmentflow.FIELD_TAST,tast);
		return new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,condition2);
	}
}
