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
import com.p8499.lang.bean.Article;
import com.p8499.lang.mask.ArticleMask;
import com.p8499.lang.mapper.ArticleMapper;

@RestController
@RequestMapping(value="/api/article",produces="application/json;charset=UTF-8")
public class ArticleController extends RestControllerBase<Article,ArticleMask,Integer>
{	@RequestMapping(value="/{atid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_ra=checkSecurity(session,"artc_ra"),artc_ri=checkSecurity(session,"artc_ri");
		if(!artc_ra&&!artc_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ArticleMask maskObj=mask==null?null:(ArticleMask)jackson.readValue(mask,ArticleMask.class);
		Article result=((ArticleMapper)bMapper).get(atid,maskObj);
		if(!artc_ra&&!result.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Article bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!artc_wa)
			bean.setAtusid(getUser(session));
		if(bean.getAtsi()==null)
			bean.setAtsi(((ArticleMapper)bMapper).nextAtsi(bean.getAtcgid()));
		bean.setAtupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAtupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ArticleMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{atid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid,@RequestBody @Validated({Update.class}) Article bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	ArticleMask maskObj=mask==null?null:(ArticleMask)jackson.readValue(mask,ArticleMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getAtid()&&result.getFieldErrorCount("atid")>0||mask!=null&&maskObj.getAtcgid()&&result.getFieldErrorCount("atcgid")>0||mask!=null&&maskObj.getAtsi()&&result.getFieldErrorCount("atsi")>0||mask!=null&&maskObj.getAtname()&&result.getFieldErrorCount("atname")>0||mask!=null&&maskObj.getAtusid()&&result.getFieldErrorCount("atusid")>0||mask!=null&&maskObj.getAtupdd()&&result.getFieldErrorCount("atupdd")>0||mask!=null&&maskObj.getAtupdt()&&result.getFieldErrorCount("atupdt")>0||mask!=null&&maskObj.getAtbrf()&&result.getFieldErrorCount("atbrf")>0||mask!=null&&maskObj.getAtcgname()&&result.getFieldErrorCount("atcgname")>0||mask!=null&&maskObj.getAtusname()&&result.getFieldErrorCount("atusname")>0||mask!=null&&maskObj.getAtcsa()&&result.getFieldErrorCount("atcsa")>0||mask!=null&&maskObj.getAtcsb()&&result.getFieldErrorCount("atcsb")>0||mask!=null&&maskObj.getAtcsc()&&result.getFieldErrorCount("atcsc")>0||mask!=null&&maskObj.getAtcsd()&&result.getFieldErrorCount("atcsd")>0||mask!=null&&maskObj.getAtcse()&&result.getFieldErrorCount("atcse")>0||mask!=null&&maskObj.getAtcsf()&&result.getFieldErrorCount("atcsf")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("artcK"+atid)&&!reserved.isReservedBy("artcK"+atid,session.getId()))
			return finish("",response,423);
		Article origBean=((ArticleMapper)bMapper).get(bean.getAtid(),null);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!artc_wa)
			bean.setAtusid(getUser(session));
		bean.setAtupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAtupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ArticleMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{atid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("artcK"+atid))
			return finish("",response,423);
		Article origBean=((ArticleMapper)bMapper).get(atid,null);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((ArticleMapper)bMapper).delete(atid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_ra=checkSecurity(session,"artc_ra"),artc_ri=checkSecurity(session,"artc_ri");
		if(!artc_ra&&!artc_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ArticleMask maskObj=mask==null?new ArticleMask().all(true):(ArticleMask)jackson.readValue(mask,ArticleMask.class);
		if(!artc_ra)
			return finish(queryRange(response,filter,orderBy,range,"atusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="articleMapper")
	public void setbMapper(BeanMapper<Article,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="articleListener")
	public void setListener(BeanListener<Article,ArticleMask> listener)
	{	super.setListener(listener);
	}
}