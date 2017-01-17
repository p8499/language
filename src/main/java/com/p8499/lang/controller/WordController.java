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
import com.p8499.lang.bean.Word;
import com.p8499.lang.mask.WordMask;
import com.p8499.lang.mapper.WordMapper;

@RestController
@RequestMapping(value="/api/word",produces="application/json;charset=UTF-8")
public class WordController extends RestControllerBase<Word,WordMask,Integer>
{	@RequestMapping(value="/{woid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer woid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean word_ra=checkSecurity(session,"word_ra");
		if(!word_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordMask maskObj=mask==null?null:(WordMask)jackson.readValue(mask,WordMask.class);
		Word result=((WordMapper)bMapper).get(woid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Word bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean word_wa=checkSecurity(session,"word_wa");
		if(!word_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((WordMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{woid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer woid,@RequestBody @Validated({Update.class}) Word bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	WordMask maskObj=mask==null?null:(WordMask)jackson.readValue(mask,WordMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getWoid()&&result.getFieldErrorCount("woid")>0||mask!=null&&maskObj.getWolsid()&&result.getFieldErrorCount("wolsid")>0||mask!=null&&maskObj.getWoct()&&result.getFieldErrorCount("woct")>0||mask!=null&&maskObj.getWopt()&&result.getFieldErrorCount("wopt")>0||mask!=null&&maskObj.getWocl()&&result.getFieldErrorCount("wocl")>0||mask!=null&&maskObj.getWosort()&&result.getFieldErrorCount("wosort")>0||mask!=null&&maskObj.getWost()&&result.getFieldErrorCount("wost")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean word_wa=checkSecurity(session,"word_wa");
		if(!word_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wordK"+woid)&&!reserved.isReservedBy("wordK"+woid,session.getId()))
			return finish("",response,423);
		Word origBean=((WordMapper)bMapper).get(bean.getWoid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((WordMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{woid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer woid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean word_wa=checkSecurity(session,"word_wa");
		if(!word_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wordK"+woid))
			return finish("",response,423);
		Word origBean=((WordMapper)bMapper).get(woid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((WordMapper)bMapper).delete(woid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean word_ra=checkSecurity(session,"word_ra");
		if(!word_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordMask maskObj=mask==null?new WordMask().all(true):(WordMask)jackson.readValue(mask,WordMask.class);
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
	@Resource(name="wordMapper")
	public void setbMapper(BeanMapper<Word,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="wordListener")
	public void setListener(BeanListener<Word,WordMask> listener)
	{	super.setListener(listener);
	}
}