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
import com.p8499.lang.bean.Language;
import com.p8499.lang.mapper.LanguageMapper;

@RestController
@RequestMapping(value="/api/language_check",produces="application/json;charset=UTF-8")
public class LanguageCheckController extends RestCheckControllerBase<Language,String>
{	@RequestMapping(value="/{lsid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Language bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(((LanguageMapper)bMapper).exists(bean.getLsid())||!((LanguageMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lsid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Language bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+bean.getLsid())&&!reserved.isReservedBy("langK"+bean.getLsid(),session.getId()))
			return finish("",response,423);
		Language origBean=((LanguageMapper)bMapper).get(bean.getLsid(),null);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!((LanguageMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{lsid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lsid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+lsid))
			return finish("",response,423);
		Language origBean=((LanguageMapper)bMapper).get(lsid,null);
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
	@Resource(name="languageMapper")
	public void setbMapper(BeanMapper<Language,String> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Language bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		return fields;
	}
	private List<List<String>> referencedAndchanged(Language origBean,Language bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!origBean.getLsid().equals(bean.getLsid()))
			if(((LanguageMapper)bMapper).referencedLsid(origBean.getLsid()))
				fields.add(java.util.Arrays.asList("lsid"));
		return fields;
	}
	private List<List<String>> referenced(Language origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(((LanguageMapper)bMapper).referencedLsid(origBean.getLsid()))
			fields.add(java.util.Arrays.asList("lsid"));
		return fields;
	}
}