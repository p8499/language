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
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.mask.SentenceMask;
import com.p8499.lang.mapper.SentenceMapper;

@RestController
@RequestMapping(value="/api/sentence",produces="application/json;charset=UTF-8")
public class SentenceController extends RestControllerBase<Sentence,SentenceMask,Integer>
{	@RequestMapping(value="/{asid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_ra=checkSecurity(session,"sent_ra"),sent_ri=checkSecurity(session,"sent_ri");
		if(!sent_ra&&!sent_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SentenceMask maskObj=mask==null?null:(SentenceMask)jackson.readValue(mask,SentenceMask.class);
		Sentence result=((SentenceMapper)bMapper).get(asid,maskObj);
		if(!sent_ra&&!result.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Sentence bean,BindingResult result) throws IllegalStateException, IOException
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
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SentenceMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{asid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid,@RequestBody @Validated({Update.class}) Sentence bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	SentenceMask maskObj=mask==null?null:(SentenceMask)jackson.readValue(mask,SentenceMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getAsid()&&result.getFieldErrorCount("asid")>0||mask!=null&&maskObj.getAsatid()&&result.getFieldErrorCount("asatid")>0||mask!=null&&maskObj.getAssi()&&result.getFieldErrorCount("assi")>0||mask!=null&&maskObj.getAscont()&&result.getFieldErrorCount("ascont")>0||mask!=null&&maskObj.getAsst()&&result.getFieldErrorCount("asst")>0||mask!=null&&maskObj.getAsusid()&&result.getFieldErrorCount("asusid")>0||mask!=null&&maskObj.getAsupdd()&&result.getFieldErrorCount("asupdd")>0||mask!=null&&maskObj.getAsupdt()&&result.getFieldErrorCount("asupdt")>0||mask!=null&&maskObj.getAscs()&&result.getFieldErrorCount("ascs")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sentK"+asid)&&!reserved.isReservedBy("sentK"+asid,session.getId()))
			return finish("",response,423);
		Sentence origBean=((SentenceMapper)bMapper).get(bean.getAsid(),null);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sent_wa)
			bean.setAsusid(getUser(session));
		bean.setAsupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAsupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SentenceMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
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
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((SentenceMapper)bMapper).delete(asid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_ra=checkSecurity(session,"sent_ra"),sent_ri=checkSecurity(session,"sent_ri");
		if(!sent_ra&&!sent_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SentenceMask maskObj=mask==null?new SentenceMask().all(true):(SentenceMask)jackson.readValue(mask,SentenceMask.class);
		if(!sent_ra)
			return finish(queryRange(response,filter,orderBy,range,"asusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="sentenceMapper")
	public void setbMapper(BeanMapper<Sentence,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="sentenceListener")
	public void setListener(BeanListener<Sentence,SentenceMask> listener)
	{	super.setListener(listener);
	}
}