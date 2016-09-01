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
import com.p8499.lang.bean.Roleauthority;
import com.p8499.lang.mapper.RoleauthorityMapper;

@RestController
@RequestMapping(value="/api/roleauthority_check",produces="application/json;charset=UTF-8")
public class RoleauthorityCheckController extends RestCheckControllerBase<Roleauthority,Integer>
{	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Roleauthority bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_wa=checkSecurity(session,"rlau_wa");
		if(!rlau_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((RoleauthorityMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{raid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Roleauthority bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_wa=checkSecurity(session,"rlau_wa");
		if(!rlau_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("rlauK"+bean.getRaid())&&!reserved.isReservedBy("rlauK"+bean.getRaid(),session.getId()))
			return finish("",response,423);
		Roleauthority origBean=((RoleauthorityMapper)bMapper).get(bean.getRaid());
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!((RoleauthorityMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{raid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer raid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_wa=checkSecurity(session,"rlau_wa");
		if(!rlau_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("rlauK"+raid))
			return finish("",response,423);
		Roleauthority origBean=((RoleauthorityMapper)bMapper).get(raid);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
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
	@Resource(name="roleauthorityMapper")
	public void setbMapper(BeanMapper<Roleauthority,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Roleauthority bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((RoleauthorityMapper)bMapper).referencingRaauid(bean.getRaauid()))
		{	fields.add(java.util.Arrays.asList("raauid"));
		}
		if(!((RoleauthorityMapper)bMapper).referencingRarlid(bean.getRarlid()))
		{	fields.add(java.util.Arrays.asList("rarlid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(Roleauthority origBean,Roleauthority bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		return fields;
	}
	private List<List<String>> referenced(Roleauthority origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		return fields;
	}
}