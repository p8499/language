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
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.mapper.SentenceMapper;

@RestController
@RequestMapping(value="/api/sentence_check",produces="application/json;charset=UTF-8")
public class SentenceCheckController extends RestCheckControllerBase<Sentence,Integer>
{	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Sentence bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((SentenceMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{asid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Sentence bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sentK"+bean.getAsid())&&!reserved.isReservedBy("sentK"+bean.getAsid(),session.getId()))
			return finish("",response,423);
		Sentence origBean=((SentenceMapper)bMapper).get(bean.getAsid(),null);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((SentenceMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{asid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sentK"+asid))
			return finish("",response,423);
		Sentence origBean=((SentenceMapper)bMapper).get(asid,null);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
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
	@Resource(name="sentenceMapper")
	public void setbMapper(BeanMapper<Sentence,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Sentence bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((SentenceMapper)bMapper).referencingAsatid(bean.getAsatid()))
		{	fields.add(java.util.Arrays.asList("asatid"));
		}
		if(!((SentenceMapper)bMapper).referencingAsusid(bean.getAsusid()))
		{	fields.add(java.util.Arrays.asList("asusid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(Sentence origBean,Sentence bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!origBean.getAsid().equals(bean.getAsid()))
			if(((SentenceMapper)bMapper).referencedAsid(origBean.getAsid()))
				fields.add(java.util.Arrays.asList("asid"));
		return fields;
	}
	private List<List<String>> referenced(Sentence origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(((SentenceMapper)bMapper).referencedAsid(origBean.getAsid()))
			fields.add(java.util.Arrays.asList("asid"));
		return fields;
	}
}