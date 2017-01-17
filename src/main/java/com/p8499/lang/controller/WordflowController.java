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
import com.p8499.lang.bean.Wordflow;
import com.p8499.lang.mask.WordflowMask;
import com.p8499.lang.mapper.WordflowMapper;

@RestController
@RequestMapping(value="/api/wordflow",produces="application/json;charset=UTF-8")
public class WordflowController extends RestControllerBase<Wordflow,WordflowMask,Integer>
{	@RequestMapping(value="/{waid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_ra=checkSecurity(session,"wdfl_ra"),wdfl_ri=checkSecurity(session,"wdfl_ri");
		if(!wdfl_ra&&!wdfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordflowMask maskObj=mask==null?null:(WordflowMask)jackson.readValue(mask,WordflowMask.class);
		Wordflow result=((WordflowMapper)bMapper).get(waid,maskObj);
		if(!wdfl_ra&&!result.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Wordflow bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdfl_wa)
			bean.setWausid(getUser(session));
		if(bean.getWasi()==null)
			bean.setWasi(((WordflowMapper)bMapper).nextWasi(bean.getWawoid()));
		bean.setWacrdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWacrdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		bean.setWaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((WordflowMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid,@RequestBody @Validated({Update.class}) Wordflow bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	WordflowMask maskObj=mask==null?null:(WordflowMask)jackson.readValue(mask,WordflowMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getWaid()&&result.getFieldErrorCount("waid")>0||mask!=null&&maskObj.getWawoid()&&result.getFieldErrorCount("wawoid")>0||mask!=null&&maskObj.getWasi()&&result.getFieldErrorCount("wasi")>0||mask!=null&&maskObj.getWapt()&&result.getFieldErrorCount("wapt")>0||mask!=null&&maskObj.getWast()&&result.getFieldErrorCount("wast")>0||mask!=null&&maskObj.getWausid()&&result.getFieldErrorCount("wausid")>0||mask!=null&&maskObj.getWacrdd()&&result.getFieldErrorCount("wacrdd")>0||mask!=null&&maskObj.getWacrdt()&&result.getFieldErrorCount("wacrdt")>0||mask!=null&&maskObj.getWaupdd()&&result.getFieldErrorCount("waupdd")>0||mask!=null&&maskObj.getWaupdt()&&result.getFieldErrorCount("waupdt")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+waid)&&!reserved.isReservedBy("wdflK"+waid,session.getId()))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(bean.getWaid(),null);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdfl_wa)
			bean.setWausid(getUser(session));
		bean.setWacrdd(origBean.getWacrdd());
		bean.setWacrdt(origBean.getWacrdt());
		bean.setWaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((WordflowMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+waid))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(waid,null);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((WordflowMapper)bMapper).delete(waid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_ra=checkSecurity(session,"wdfl_ra"),wdfl_ri=checkSecurity(session,"wdfl_ri");
		if(!wdfl_ra&&!wdfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordflowMask maskObj=mask==null?new WordflowMask().all(true):(WordflowMask)jackson.readValue(mask,WordflowMask.class);
		if(!wdfl_ra)
			return finish(queryRange(response,filter,orderBy,range,"wausid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="wordflowMapper")
	public void setbMapper(BeanMapper<Wordflow,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="wordflowListener")
	public void setListener(BeanListener<Wordflow,WordflowMask> listener)
	{	super.setListener(listener);
	}
}