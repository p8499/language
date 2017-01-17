package com.p8499.lang.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.mvc.BeanListener;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.RestControllerBase;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.mvc.database.Update;
import com.p8499.lang.bean.Segmentflow;
import com.p8499.lang.mask.SegmentflowMask;
import com.p8499.lang.mapper.SegmentflowMapper;

@RestController
@RequestMapping(value="/api/segmentflow",produces="application/json;charset=UTF-8")
public class SegmentflowController extends RestControllerBase<Segmentflow,SegmentflowMask,Integer>
{	@RequestMapping(value="/{taid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_ra=checkSecurity(session,"sgfl_ra"),sgfl_ri=checkSecurity(session,"sgfl_ri");
		if(!sgfl_ra&&!sgfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentflowMask maskObj=mask==null?null:(SegmentflowMask)jackson.readValue(mask,SegmentflowMask.class);
		Segmentflow result=((SegmentflowMapper)bMapper).get(taid,maskObj);
		if(!sgfl_ra&&!result.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Segmentflow bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_wa=checkSecurity(session,"sgfl_wa"),sgfl_wi=checkSecurity(session,"sgfl_wi");
		if(!sgfl_wa&&!sgfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgfl_wa)
			bean.setTausid(getUser(session));
		if(bean.getTasi()==null)
			bean.setTasi(((SegmentflowMapper)bMapper).nextTasi(bean.getTaasid()));
		bean.setTacrdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTacrdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		bean.setTaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentflowMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{taid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid,@RequestBody @Validated({Update.class}) Segmentflow bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	SegmentflowMask maskObj=mask==null?null:(SegmentflowMask)jackson.readValue(mask,SegmentflowMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getTaid()&&result.getFieldErrorCount("taid")>0||mask!=null&&maskObj.getTaasid()&&result.getFieldErrorCount("taasid")>0||mask!=null&&maskObj.getTasi()&&result.getFieldErrorCount("tasi")>0||mask!=null&&maskObj.getTapi()&&result.getFieldErrorCount("tapi")>0||mask!=null&&maskObj.getTahz()&&result.getFieldErrorCount("tahz")>0||mask!=null&&maskObj.getTast()&&result.getFieldErrorCount("tast")>0||mask!=null&&maskObj.getTausid()&&result.getFieldErrorCount("tausid")>0||mask!=null&&maskObj.getTacrdd()&&result.getFieldErrorCount("tacrdd")>0||mask!=null&&maskObj.getTacrdt()&&result.getFieldErrorCount("tacrdt")>0||mask!=null&&maskObj.getTaupdd()&&result.getFieldErrorCount("taupdd")>0||mask!=null&&maskObj.getTaupdt()&&result.getFieldErrorCount("taupdt")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_wa=checkSecurity(session,"sgfl_wa"),sgfl_wi=checkSecurity(session,"sgfl_wi");
		if(!sgfl_wa&&!sgfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgflK"+taid)&&!reserved.isReservedBy("sgflK"+taid,session.getId()))
			return finish("",response,423);
		Segmentflow origBean=((SegmentflowMapper)bMapper).get(bean.getTaid(),null);
		if(!sgfl_wa&&!origBean.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgfl_wa)
			bean.setTausid(getUser(session));
		bean.setTacrdd(origBean.getTacrdd());
		bean.setTacrdt(origBean.getTacrdt());
		bean.setTaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentflowMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{taid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_wa=checkSecurity(session,"sgfl_wa"),sgfl_wi=checkSecurity(session,"sgfl_wi");
		if(!sgfl_wa&&!sgfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgflK"+taid))
			return finish("",response,423);
		Segmentflow origBean=((SegmentflowMapper)bMapper).get(taid,null);
		if(!sgfl_wa&&!origBean.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((SegmentflowMapper)bMapper).delete(taid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_ra=checkSecurity(session,"sgfl_ra"),sgfl_ri=checkSecurity(session,"sgfl_ri");
		if(!sgfl_ra&&!sgfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentflowMask maskObj=mask==null?new SegmentflowMask().all(true):(SegmentflowMask)jackson.readValue(mask,SegmentflowMask.class);
		if(!sgfl_ra)
			return finish(queryRange(response,filter,orderBy,range,"tausid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
		return finish(queryRange(response,filter,orderBy,range,maskObj),response,HttpURLConnection.HTTP_OK);
	}
	@Resource(name="jackson")
	public void setJackson(ObjectMapper jackson)
	{	super.setJackson(jackson);
	}
	@Resource(name="md5Encryptor")
	public void setEncryptor(MD5Encryptor encryptor)
	{	super.setEncryptor(encryptor);
	}
	@Resource(name="appMapper")
	public void settMapper(ToolMapper tMapper)
	{	super.settMapper(tMapper);
	}
	@Resource(name="segmentflowMapper")
	public void setbMapper(BeanMapper<Segmentflow,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="segmentflowListener")
	public void setListener(BeanListener<Segmentflow,SegmentflowMask> listener)
	{	super.setListener(listener);
	}
}