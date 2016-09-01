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
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.mask.SentenceMask;
import com.p8499.lang.mapper.SentenceMapper;

@RestController
@RequestMapping(value="/api/sentence",produces="application/json;charset=UTF-8")
public class SentenceController extends RestControllerBase<Sentence,SentenceMask,Integer>
{	@RequestMapping(value="/{asid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_ra=checkSecurity(session,"sent_ra"),sent_ri=checkSecurity(session,"sent_ri");
		if(!sent_ra&&!sent_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Sentence result=((SentenceMapper)bMapper).get(asid);
		if(!sent_ra&&!result.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Sentence bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sent_wa)
			bean.setAsusid(getUser(session));
		if(bean.getAssi()==null)
			bean.setAssi(((SentenceMapper)bMapper).nextAssi(bean.getAsatid()));
		bean.setAsupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAsupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeAdd(bean);
		((SentenceMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
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
		Sentence origBean=((SentenceMapper)bMapper).get(bean.getAsid());
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sent_wa)
			bean.setAsusid(getUser(session));
		bean.setAsupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAsupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((SentenceMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
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
		Sentence origBean=((SentenceMapper)bMapper).get(asid);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(asid);
		boolean success=((SentenceMapper)bMapper).delete(asid);
		getListener().afterDelete(asid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_ra=checkSecurity(session,"sent_ra"),sent_ri=checkSecurity(session,"sent_ri");
		if(!sent_ra&&!sent_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sent_ra)
			return finish(queryRange(response,filter,orderBy,range,"asusid",getUser(session)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="sentenceMapper")
	public void setbMapper(BeanMapper<Sentence,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="sentenceListener")
	public void setListener(BeanListener<Sentence,SentenceMask,Integer> listener)
	{	super.setListener(listener);
	}
}