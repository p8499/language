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
import com.p8499.lang.bean.Languageparameter;
import com.p8499.lang.mask.LanguageparameterMask;
import com.p8499.lang.mapper.LanguageparameterMapper;

@RestController
@RequestMapping(value="/api/languageparameter",produces="application/json;charset=UTF-8")
public class LanguageparameterController extends RestControllerBase<Languageparameter,LanguageparameterMask,String>
{	@RequestMapping(value="/{lplsid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lplsid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_ra=checkSecurity(session,"lang_ra");
		if(!lang_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		LanguageparameterMask maskObj=mask==null?null:(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class);
		Languageparameter result=((LanguageparameterMapper)bMapper).get(lplsid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lplsid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Languageparameter bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((LanguageparameterMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lplsid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lplsid,@RequestBody @Validated({Update.class}) Languageparameter bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	LanguageparameterMask maskObj=mask==null?null:(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getLplsid()&&result.getFieldErrorCount("lplsid")>0||mask!=null&&maskObj.getLpsgng()&&result.getFieldErrorCount("lpsgng")>0||mask!=null&&maskObj.getLpsgch()&&result.getFieldErrorCount("lpsgch")>0||mask!=null&&maskObj.getLpsglf()&&result.getFieldErrorCount("lpsglf")>0||mask!=null&&maskObj.getLpsgcw()&&result.getFieldErrorCount("lpsgcw")>0||mask!=null&&maskObj.getLpsgbw()&&result.getFieldErrorCount("lpsgbw")>0||mask!=null&&maskObj.getLpsgnb()&&result.getFieldErrorCount("lpsgnb")>0||mask!=null&&maskObj.getLpsgft()&&result.getFieldErrorCount("lpsgft")>0||mask!=null&&maskObj.getLpsgfc()&&result.getFieldErrorCount("lpsgfc")>0||mask!=null&&maskObj.getLppong()&&result.getFieldErrorCount("lppong")>0||mask!=null&&maskObj.getLppoch()&&result.getFieldErrorCount("lppoch")>0||mask!=null&&maskObj.getLppolf()&&result.getFieldErrorCount("lppolf")>0||mask!=null&&maskObj.getLppoft()&&result.getFieldErrorCount("lppoft")>0||mask!=null&&maskObj.getLppofc()&&result.getFieldErrorCount("lppofc")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+lplsid)&&!reserved.isReservedBy("langK"+lplsid,session.getId()))
			return finish("",response,423);
		Languageparameter origBean=((LanguageparameterMapper)bMapper).get(bean.getLplsid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((LanguageparameterMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lplsid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lplsid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+lplsid))
			return finish("",response,423);
		Languageparameter origBean=((LanguageparameterMapper)bMapper).get(lplsid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((LanguageparameterMapper)bMapper).delete(lplsid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_ra=checkSecurity(session,"lang_ra");
		if(!lang_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		LanguageparameterMask maskObj=mask==null?new LanguageparameterMask().all(true):(LanguageparameterMask)jackson.readValue(mask,LanguageparameterMask.class);
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
	@Resource(name="languageparameterMapper")
	public void setbMapper(BeanMapper<Languageparameter,String> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="languageparameterListener")
	public void setListener(BeanListener<Languageparameter,LanguageparameterMask> listener)
	{	super.setListener(listener);
	}
}