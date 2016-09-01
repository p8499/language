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
import com.p8499.lang.bean.User;
import com.p8499.lang.mapper.UserMapper;
import com.p8499.lang.mask.UserMask;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.MaskControllerBase;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.mvc.database.Update;

@RestController
@RequestMapping(value="/api/user_mask",produces="application/json;charset=UTF-8")
public class UserMaskController extends MaskControllerBase<User,UserMask,String>
{	@RequestMapping(value="/{usid}",method=RequestMethod.GET)
	public String getWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_ra=checkSecurity(session,"user_ra"),user_ri=checkSecurity(session,"user_ri");
		if(!user_ra&&!user_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		User result=((UserMapper)bMapper).getWithMask(usid,(UserMask)jackson.readValue(mask,UserMask.class));
		if(!user_ra&&!result.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.PUT)
	public String updateWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) User bean,BindingResult result,@RequestParam("mask")String mask) throws IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+bean.getUsid())&&!reserved.isReservedBy("userK"+bean.getUsid(),session.getId()))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(bean.getUsid());
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!user_wa)
			bean.setUsid(getUser(session));
		UserMask maskObj=(UserMask)jackson.readValue(mask,UserMask.class);
		getListener().beforeUpdateWithMask(bean,maskObj);
		((UserMapper)bMapper).updateWithMask(bean,maskObj);
		getListener().afterUpdateWithMask(bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String queryWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_ra=checkSecurity(session,"user_ra"),user_ri=checkSecurity(session,"user_ri");
		if(!user_ra&&!user_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!user_ra)
			return finish(queryRange(response,filter,orderBy,range,"usid",getUser(session),(UserMask)jackson.readValue(mask,UserMask.class)),response,HttpURLConnection.HTTP_OK);
		return finish(queryRange(response,filter,orderBy,range,(UserMask)jackson.readValue(mask,UserMask.class)),response,HttpURLConnection.HTTP_OK);
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
	public void setListener(BeanListener<User,UserMask,String> listener)
	{	super.setListener(listener);
	}
}