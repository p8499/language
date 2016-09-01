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
import com.p8499.lang.bean.Wordflow;
import com.p8499.lang.mapper.WordflowMapper;

@RestController
@RequestMapping(value="/api/wordflow_check",produces="application/json;charset=UTF-8")
public class WordflowCheckController extends RestCheckControllerBase<Wordflow,Integer>
{	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Wordflow bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((WordflowMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Wordflow bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+bean.getWaid())&&!reserved.isReservedBy("wdflK"+bean.getWaid(),session.getId()))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(bean.getWaid());
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((WordflowMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+waid))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(waid);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
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
	@Resource(name="wordflowMapper")
	public void setbMapper(BeanMapper<Wordflow,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Wordflow bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((WordflowMapper)bMapper).referencingWawoid(bean.getWawoid()))
		{	fields.add(java.util.Arrays.asList("wawoid"));
		}
		if(!((WordflowMapper)bMapper).referencingWausid(bean.getWausid()))
		{	fields.add(java.util.Arrays.asList("wausid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(Wordflow origBean,Wordflow bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!origBean.getWaid().equals(bean.getWaid()))
			if(((WordflowMapper)bMapper).referencedWaid(origBean.getWaid()))
				fields.add(java.util.Arrays.asList("waid"));
		return fields;
	}
	private List<List<String>> referenced(Wordflow origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(((WordflowMapper)bMapper).referencedWaid(origBean.getWaid()))
			fields.add(java.util.Arrays.asList("waid"));
		return fields;
	}
}