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
import com.p8499.lang.bean.Pronounce;
import com.p8499.lang.mask.PronounceMask;
import com.p8499.lang.mapper.PronounceMapper;

@RestController
@RequestMapping(value="/api/pronounce",produces="application/json;charset=UTF-8")
public class PronounceController extends RestControllerBase<Pronounce,PronounceMask,Integer>
{	@RequestMapping(value="/{pnid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer pnid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean pron_ra=checkSecurity(session,"pron_ra");
		if(!pron_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		PronounceMask maskObj=mask==null?null:(PronounceMask)jackson.readValue(mask,PronounceMask.class);
		Pronounce result=((PronounceMapper)bMapper).get(pnid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Pronounce bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean pron_wa=checkSecurity(session,"pron_wa");
		if(!pron_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((PronounceMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{pnid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer pnid,@RequestBody @Validated({Update.class}) Pronounce bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	PronounceMask maskObj=mask==null?null:(PronounceMask)jackson.readValue(mask,PronounceMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getPnid()&&result.getFieldErrorCount("pnid")>0||mask!=null&&maskObj.getPnlsid()&&result.getFieldErrorCount("pnlsid")>0||mask!=null&&maskObj.getPnct()&&result.getFieldErrorCount("pnct")>0||mask!=null&&maskObj.getPnpi()&&result.getFieldErrorCount("pnpi")>0||mask!=null&&maskObj.getPntn()&&result.getFieldErrorCount("pntn")>0||mask!=null&&maskObj.getPnco()&&result.getFieldErrorCount("pnco")>0||mask!=null&&maskObj.getPnvo()&&result.getFieldErrorCount("pnvo")>0||mask!=null&&maskObj.getPncl()&&result.getFieldErrorCount("pncl")>0||mask!=null&&maskObj.getPnrm()&&result.getFieldErrorCount("pnrm")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean pron_wa=checkSecurity(session,"pron_wa");
		if(!pron_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("pronK"+pnid)&&!reserved.isReservedBy("pronK"+pnid,session.getId()))
			return finish("",response,423);
		Pronounce origBean=((PronounceMapper)bMapper).get(bean.getPnid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((PronounceMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{pnid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer pnid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean pron_wa=checkSecurity(session,"pron_wa");
		if(!pron_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("pronK"+pnid))
			return finish("",response,423);
		Pronounce origBean=((PronounceMapper)bMapper).get(pnid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((PronounceMapper)bMapper).delete(pnid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean pron_ra=checkSecurity(session,"pron_ra");
		if(!pron_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		PronounceMask maskObj=mask==null?new PronounceMask().all(true):(PronounceMask)jackson.readValue(mask,PronounceMask.class);
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
	@Resource(name="pronounceMapper")
	public void setbMapper(BeanMapper<Pronounce,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="pronounceListener")
	public void setListener(BeanListener<Pronounce,PronounceMask> listener)
	{	super.setListener(listener);
	}
}