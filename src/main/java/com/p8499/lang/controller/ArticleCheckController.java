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
import com.p8499.lang.bean.Article;
import com.p8499.lang.mapper.ArticleMapper;

@RestController
@RequestMapping(value="/api/article_check",produces="application/json;charset=UTF-8")
public class ArticleCheckController extends RestCheckControllerBase<Article,Integer>
{	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Article bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((ArticleMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
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
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((ArticleMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
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
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
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
	@Resource(name="articleMapper")
	public void setbMapper(BeanMapper<Article,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Article bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((ArticleMapper)bMapper).referencingAtcgid(bean.getAtcgid()))
		{	fields.add(java.util.Arrays.asList("atcgid"));
		}
		if(!((ArticleMapper)bMapper).referencingAtusid(bean.getAtusid()))
		{	fields.add(java.util.Arrays.asList("atusid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(Article origBean,Article bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!origBean.getAtid().equals(bean.getAtid()))
			if(((ArticleMapper)bMapper).referencedAtid(origBean.getAtid()))
				fields.add(java.util.Arrays.asList("atid"));
		return fields;
	}
	private List<List<String>> referenced(Article origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(((ArticleMapper)bMapper).referencedAtid(origBean.getAtid()))
			fields.add(java.util.Arrays.asList("atid"));
		return fields;
	}
}