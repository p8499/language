package com.p8499.lang.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.mvc.ControllerBase;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.lang.bean.User;
import com.p8499.lang.mapper.UserMapper;

@RestController
@RequestMapping(value="/api/base",produces="application/json;charset=UTF-8")
public class AppController extends ControllerBase
{	@RequestMapping("/signin")
	public String signin(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String usid,@RequestParam String uspswd) throws UnsupportedEncodingException, JsonProcessingException
	{	User u=userMapper.get(usid);
		if(u==null||!u.getUspswd().equals(encryptor.entrypt(uspswd)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		setUser(session,u.getUsid());
		u.setUspswd(null);
		return finish(u,response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/status")
	public String status(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		User u=userMapper.get((String)session.getAttribute("user"));
		u.setUspswd(null);
		return finish(u,response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/security")
	public String security(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String auth) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,auth))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/encrypt")
	public String encrypt(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String plain) throws JsonProcessingException, UnsupportedEncodingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		return finish(encryptor.entrypt(plain),response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/lock")
	public String lock(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String obj,@RequestParam String id) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,obj+"_wa")&&!checkSecurity(session,obj+"_wi"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!reserved.reserve(obj+"K"+id,session.getId()))
			return finish("",response,423);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/unlock")
	public String unlock(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String obj,@RequestParam String id) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,obj+"_wa")&&!checkSecurity(session,obj+"_wi"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!reserved.remove(obj+"K"+id,session.getId()))
			return finish("",response,408);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/signout")
	public String signout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		setUser(session,null);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/build")
	public String build(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String lang) throws ClassNotFoundException, SQLException, IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,"lang_wa"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
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
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="appMapper")
	public void settMapper(ToolMapper tMapper)
	{	super.settMapper(tMapper);
	}
	@Resource(name="userMapper")
	public UserMapper userMapper=null;
}
