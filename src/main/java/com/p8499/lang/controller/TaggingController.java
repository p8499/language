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
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.RestControllerBase;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.BeanListener;
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
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_ra=checkSecurity(session,"post_ra"),post_ri=checkSecurity(session,"post_ri");
		if(!post_ra&&!post_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Tagging result=((TaggingMapper)bMapper).get(tgasid);
		if(!post_ra&&!result.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Tagging bean,BindingResult result) throws JsonProcessingException
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
		getListener().beforeAdd(bean);
		((TaggingMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Tagging bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("postK"+bean.getTgasid())&&!reserved.isReservedBy("postK"+bean.getTgasid(),session.getId()))
			return finish("",response,423);
		Tagging origBean=((TaggingMapper)bMapper).get(bean.getTgasid());
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!post_wa)
			bean.setTgusid(getUser(session));
		bean.setTgupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTgupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((TaggingMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
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
		Tagging origBean=((TaggingMapper)bMapper).get(tgasid);
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(tgasid);
		boolean success=((TaggingMapper)bMapper).delete(tgasid);
		getListener().afterDelete(tgasid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_ra=checkSecurity(session,"post_ra"),post_ri=checkSecurity(session,"post_ri");
		if(!post_ra&&!post_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!post_ra)
			return finish(queryRange(response,filter,orderBy,range,"tgusid",getUser(session)),response,HttpURLConnection.HTTP_OK);
		return finish(queryRange(response,filter,orderBy,range),response,HttpURLConnection.HTTP_OK);
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
	public void setListener(BeanListener<Tagging,TaggingMask,Integer> listener)
	{	super.setListener(listener);
	}
}