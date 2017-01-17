package com.p8499.lang.controller;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.RestCheckControllerBase;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.mvc.database.Update;
import com.p8499.lang.bean.User;
import com.p8499.lang.mapper.UserMapper;

@RestController
@RequestMapping(value="/api/user_check",produces="application/json;charset=UTF-8")
public class UserCheckController extends RestCheckControllerBase<User,String>
{	@RequestMapping(value="/{usid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) User bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(((UserMapper)bMapper).exists(bean.getUsid())||!((UserMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) User bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+bean.getUsid())&&!reserved.isReservedBy("userK"+bean.getUsid(),session.getId()))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(bean.getUsid(),null);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((UserMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
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
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		List<List<String>> referencedErrors=referenced(origBean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
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
	private List<List<String>> referencing(User bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((UserMapper)bMapper).referencingUslsid(bean.getUslsid()))
		{	fields.add(java.util.Arrays.asList("uslsid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(User origBean,User bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!origBean.getUsid().equals(bean.getUsid()))
			if(((UserMapper)bMapper).referencedUsid(origBean.getUsid()))
				fields.add(java.util.Arrays.asList("usid"));
		return fields;
	}
	private List<List<String>> referenced(User origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(((UserMapper)bMapper).referencedUsid(origBean.getUsid()))
			fields.add(java.util.Arrays.asList("usid"));
		return fields;
	}
}