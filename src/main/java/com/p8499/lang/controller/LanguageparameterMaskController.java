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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.lang.bean.Languageparameter;
import com.p8499.lang.mapper.LanguageparameterMapper;
import com.p8499.lang.mask.LanguageparameterMask;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.MaskControllerBase;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.mvc.database.Update;

@RestController
@RequestMapping(value="/api/languageparameter_mask",produces="application/json;charset=UTF-8")
public class LanguageparameterMaskController extends MaskControllerBase<Languageparameter,LanguageparameterMask,String>
{	@RequestMapping(value="/{lplsid}",method=RequestMethod.GET)
	public String getWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lplsid,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_ra=checkSecurity(session,"lang_ra");
		if(!lang_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Languageparameter result=((LanguageparameterMapper)bMapper).getWithMask(lplsid,(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class));
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lplsid}",method=RequestMethod.PUT)
	public String updateWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Languageparameter bean,BindingResult result,@RequestParam("mask")String mask) throws IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+bean.getLplsid())&&!reserved.isReservedBy("langK"+bean.getLplsid(),session.getId()))
			return finish("",response,423);
		LanguageparameterMask maskObj=(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class);
		getListener().beforeUpdateWithMask(bean,maskObj);
		((LanguageparameterMapper)bMapper).updateWithMask(bean,maskObj);
		getListener().afterUpdateWithMask(bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String queryWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_ra=checkSecurity(session,"lang_ra");
		if(!lang_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(queryRange(response,filter,orderBy,range,(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="languageparameterMapper")
	public void setbMapper(BeanMapper<Languageparameter,String> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="languageparameterListener")
	public void setListener(BeanListener<Languageparameter,LanguageparameterMask,String> listener)
	{	super.setListener(listener);
	}
}