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
import com.p8499.lang.bean.User;
import com.p8499.lang.mask.UserMask;
import com.p8499.lang.mapper.UserMapper;

@RestController
@RequestMapping(value="/api/user",produces="application/json;charset=UTF-8")
public class UserController extends RestControllerBase<User,UserMask,String>
{	@RequestMapping(value="/{usid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_ra=checkSecurity(session,"user_ra"),user_ri=checkSecurity(session,"user_ri");
		if(!user_ra&&!user_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UserMask maskObj=mask==null?null:(UserMask)jackson.readValue(mask,UserMask.class);
		User result=((UserMapper)bMapper).get(usid,maskObj);
		if(!user_ra&&!result.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) User bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!user_wa)
			bean.setUsid(getUser(session));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((UserMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid,@RequestBody @Validated({Update.class}) User bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	UserMask maskObj=mask==null?null:(UserMask)jackson.readValue(mask,UserMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getUsid()&&result.getFieldErrorCount("usid")>0||mask!=null&&maskObj.getUspswd()&&result.getFieldErrorCount("uspswd")>0||mask!=null&&maskObj.getUsname()&&result.getFieldErrorCount("usname")>0||mask!=null&&maskObj.getUsst()&&result.getFieldErrorCount("usst")>0||mask!=null&&maskObj.getUslsid()&&result.getFieldErrorCount("uslsid")>0||mask!=null&&maskObj.getUspn()&&result.getFieldErrorCount("uspn")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+usid)&&!reserved.isReservedBy("userK"+usid,session.getId()))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(bean.getUsid(),null);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!user_wa)
			bean.setUsid(getUser(session));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((UserMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+usid))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(usid,null);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((UserMapper)bMapper).delete(usid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_ra=checkSecurity(session,"user_ra"),user_ri=checkSecurity(session,"user_ri");
		if(!user_ra&&!user_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UserMask maskObj=mask==null?new UserMask().all(true):(UserMask)jackson.readValue(mask,UserMask.class);
		if(!user_ra)
			return finish(queryRange(response,filter,orderBy,range,"usid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="userMapper")
	public void setbMapper(BeanMapper<User,String> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="userListener")
	public void setListener(BeanListener<User,UserMask> listener)
	{	super.setListener(listener);
	}
}