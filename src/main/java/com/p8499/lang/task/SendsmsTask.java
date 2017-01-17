package com.p8499.lang.task;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.mysms.sdk.SendMsg;
import com.p8499.lang.bean.Usercreation;
import com.p8499.lang.mapper.UsercreationMapper;
import com.p8499.lang.mask.UsercreationMask;
import com.p8499.mvc.FilterConditionExpr;
import com.p8499.mvc.FilterLogicExpr;

@Component("sendsmsTask")
public class SendsmsTask
{	@Scheduled(fixedDelay=1000)
	public void start()
	{	List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(Usercreation.UCST_WAITING).toString(),null,0,100,new UsercreationMask().setUcid(true).setUcusid(true));
		for(Usercreation uc:ucList)
		{	//send
			uc.setUcmv(String.format("%06d",new Random().nextInt(1000000)));
			uc.setUcst(Usercreation.UCST_SENT);
			String result=SendMsg.sendBatch(smsServer,smsUsername,smsPassword,uc.getUcusid(),String.format(smsFormat,uc.getUcmv()));
			
			usercreationMapper.update(uc,new UsercreationMask().setUcmv(true).setUcst(true));
		}
	}
	
	@Value(value="#{usercreationMapper}")
	private UsercreationMapper usercreationMapper;
	private static FilterLogicExpr filterUsercreation(Integer ucst)
	{	FilterConditionExpr condition1=FilterConditionExpr.equalsNumber(Usercreation.FIELD_UCST,ucst);
		return new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1);
	}
	@Value(value="${sms.server}")
	private String smsServer;
	@Value(value="${sms.username}")
	private String smsUsername;
	@Value(value="${sms.password}")
	private String smsPassword;
	@Value(value="${sms.format}")
	private String smsFormat;
	
}
