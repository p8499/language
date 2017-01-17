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
import com.p8499.lang.bean.Tagging;
import com.p8499.lang.mask.TaggingMask;
import com.p8499.lang.mapper.TaggingMapper;

@RestController
@RequestMapping(value="/api/tagging",produces="application/json;charset=UTF-8")
public class TaggingController extends RestControllerBase<Tagging,TaggingMask,Integer>
{	@RequestMapping(value="/{tgasid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_ra=checkSecurity(session,"post_ra"),post_ri=checkSecurity(session,"post_ri");
		if(!post_ra&&!post_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		TaggingMask maskObj=mask==null?null:(TaggingMask)jackson.readValue(mask,TaggingMask.class);
		Tagging result=((TaggingMapper)bMapper).get(tgasid,maskObj);
		if(!post_ra&&!result.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Tagging bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!post_wa)
			bean.setTgusid(getUser(session));
		bean.setTgupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTgupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((TaggingMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid,@RequestBody @Validated({Update.class}) Tagging bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	TaggingMask maskObj=mask==null?null:(TaggingMask)jackson.readValue(mask,TaggingMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getTgasid()&&result.getFieldErrorCount("tgasid")>0||mask!=null&&maskObj.getTgcont()&&result.getFieldErrorCount("tgcont")>0||mask!=null&&maskObj.getTgst()&&result.getFieldErrorCount("tgst")>0||mask!=null&&maskObj.getTgusid()&&result.getFieldErrorCount("tgusid")>0||mask!=null&&maskObj.getTgupdd()&&result.getFieldErrorCount("tgupdd")>0||mask!=null&&maskObj.getTgupdt()&&result.getFieldErrorCount("tgupdt")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("postK"+tgasid)&&!reserved.isReservedBy("postK"+tgasid,session.getId()))
			return finish("",response,423);
		Tagging origBean=((TaggingMapper)bMapper).get(bean.getTgasid(),null);
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!post_wa)
			bean.setTgusid(getUser(session));
		bean.setTgupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTgupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((TaggingMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("postK"+tgasid))
			return finish("",response,423);
		Tagging origBean=((TaggingMapper)bMapper).get(tgasid,null);
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((TaggingMapper)bMapper).delete(tgasid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_ra=checkSecurity(session,"post_ra"),post_ri=checkSecurity(session,"post_ri");
		if(!post_ra&&!post_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		TaggingMask maskObj=mask==null?new TaggingMask().all(true):(TaggingMask)jackson.readValue(mask,TaggingMask.class);
		if(!post_ra)
			return finish(queryRange(response,filter,orderBy,range,"tgusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="taggingMapper")
	public void setbMapper(BeanMapper<Tagging,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="taggingListener")
	public void setListener(BeanListener<Tagging,TaggingMask> listener)
	{	super.setListener(listener);
	}
}