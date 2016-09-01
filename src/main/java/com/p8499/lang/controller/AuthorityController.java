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
import com.p8499.lang.bean.Authority;
import com.p8499.lang.mask.AuthorityMask;
import com.p8499.lang.mapper.AuthorityMapper;

@RestController
@RequestMapping(value="/api/authority",produces="application/json;charset=UTF-8")
public class AuthorityController extends RestControllerBase<Authority,AuthorityMask,String>
{	@RequestMapping(value="/{auid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String auid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean auth_ra=checkSecurity(session,"auth_ra");
		if(!auth_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Authority result=((AuthorityMapper)bMapper).get(auid);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{auid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Authority bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean auth_wa=checkSecurity(session,"auth_wa");
		if(!auth_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeAdd(bean);
		((AuthorityMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{auid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Authority bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean auth_wa=checkSecurity(session,"auth_wa");
		if(!auth_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("authK"+bean.getAuid())&&!reserved.isReservedBy("authK"+bean.getAuid(),session.getId()))
			return finish("",response,423);
		getListener().beforeUpdate(bean);
		((AuthorityMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{auid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String auid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean auth_wa=checkSecurity(session,"auth_wa");
		if(!auth_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("authK"+auid))
			return finish("",response,423);
		getListener().beforeDelete(auid);
		boolean success=((AuthorityMapper)bMapper).delete(auid);
		getListener().afterDelete(auid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean auth_ra=checkSecurity(session,"auth_ra");
		if(!auth_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
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
	@Resource(name="authorityMapper")
	public void setbMapper(BeanMapper<Authority,String> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="authorityListener")
	public void setListener(BeanListener<Authority,AuthorityMask,String> listener)
	{	super.setListener(listener);
	}
}