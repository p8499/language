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
import com.p8499.lang.bean.Article;
import com.p8499.lang.mask.ArticleMask;
import com.p8499.lang.mapper.ArticleMapper;

@RestController
@RequestMapping(value="/api/article",produces="application/json;charset=UTF-8")
public class ArticleController extends RestControllerBase<Article,ArticleMask,Integer>
{	@RequestMapping(value="/{atid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_ra=checkSecurity(session,"artc_ra"),artc_ri=checkSecurity(session,"artc_ri");
		if(!artc_ra&&!artc_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Article result=((ArticleMapper)bMapper).get(atid);
		if(!artc_ra&&!result.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Article bean,BindingResult result) throws JsonProcessingException
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
		getListener().beforeAdd(bean);
		((ArticleMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{atid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Article bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("artcK"+bean.getAtid())&&!reserved.isReservedBy("artcK"+bean.getAtid(),session.getId()))
			return finish("",response,423);
		Article origBean=((ArticleMapper)bMapper).get(bean.getAtid());
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!artc_wa)
			bean.setAtusid(getUser(session));
		bean.setAtupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setAtupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((ArticleMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
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
		Article origBean=((ArticleMapper)bMapper).get(atid);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(atid);
		boolean success=((ArticleMapper)bMapper).delete(atid);
		getListener().afterDelete(atid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_ra=checkSecurity(session,"artc_ra"),artc_ri=checkSecurity(session,"artc_ri");
		if(!artc_ra&&!artc_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!artc_ra)
			return finish(queryRange(response,filter,orderBy,range,"atusid",getUser(session)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="articleMapper")
	public void setbMapper(BeanMapper<Article,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="articleListener")
	public void setListener(BeanListener<Article,ArticleMask,Integer> listener)
	{	super.setListener(listener);
	}
}