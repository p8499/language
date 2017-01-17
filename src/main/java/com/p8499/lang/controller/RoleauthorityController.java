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
import com.p8499.lang.bean.Roleauthority;
import com.p8499.lang.mask.RoleauthorityMask;
import com.p8499.lang.mapper.RoleauthorityMapper;

@RestController
@RequestMapping(value="/api/roleauthority",produces="application/json;charset=UTF-8")
public class RoleauthorityController extends RestControllerBase<Roleauthority,RoleauthorityMask,Integer>
{	@RequestMapping(value="/{raid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer raid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_ra=checkSecurity(session,"rlau_ra");
		if(!rlau_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		RoleauthorityMask maskObj=mask==null?null:(RoleauthorityMask)jackson.readValue(mask,RoleauthorityMask.class);
		Roleauthority result=((RoleauthorityMapper)bMapper).get(raid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Roleauthority bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_wa=checkSecurity(session,"rlau_wa");
		if(!rlau_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((RoleauthorityMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{raid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer raid,@RequestBody @Validated({Update.class}) Roleauthority bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	RoleauthorityMask maskObj=mask==null?null:(RoleauthorityMask)jackson.readValue(mask,RoleauthorityMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getRaid()&&result.getFieldErrorCount("raid")>0||mask!=null&&maskObj.getRarlid()&&result.getFieldErrorCount("rarlid")>0||mask!=null&&maskObj.getRaauid()&&result.getFieldErrorCount("raauid")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_wa=checkSecurity(session,"rlau_wa");
		if(!rlau_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("rlauK"+raid)&&!reserved.isReservedBy("rlauK"+raid,session.getId()))
			return finish("",response,423);
		Roleauthority origBean=((RoleauthorityMapper)bMapper).get(bean.getRaid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((RoleauthorityMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
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
		Roleauthority origBean=((RoleauthorityMapper)bMapper).get(raid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((RoleauthorityMapper)bMapper).delete(raid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean rlau_ra=checkSecurity(session,"rlau_ra");
		if(!rlau_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		RoleauthorityMask maskObj=mask==null?new RoleauthorityMask().all(true):(RoleauthorityMask)jackson.readValue(mask,RoleauthorityMask.class);
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
	@Resource(name="roleauthorityMapper")
	public void setbMapper(BeanMapper<Roleauthority,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="roleauthorityListener")
	public void setListener(BeanListener<Roleauthority,RoleauthorityMask> listener)
	{	super.setListener(listener);
	}
}