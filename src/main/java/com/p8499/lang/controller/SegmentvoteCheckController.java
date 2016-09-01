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
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.mapper.SegmentvoteMapper;

@RestController
@RequestMapping(value="/api/segmentvote_check",produces="application/json;charset=UTF-8")
public class SegmentvoteCheckController extends RestCheckControllerBase<Segmentvote,Integer>
{	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Segmentvote bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((SegmentvoteMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Segmentvote bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgvtK"+bean.getTvid())&&!reserved.isReservedBy("sgvtK"+bean.getTvid(),session.getId()))
			return finish("",response,423);
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(bean.getTvid());
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!((SegmentvoteMapper)bMapper).unique(bean))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<List<String>> referencingErrors=referencing(bean);
		if(referencingErrors.size()>0)
			return finish(referencingErrors,response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		List<List<String>> referencedErrors=referencedAndchanged(origBean,bean);
		if(referencedErrors.size()>0)
			return finish(referencedErrors,response,HttpURLConnection.HTTP_PRECON_FAILED);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgvtK"+tvid))
			return finish("",response,423);
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(tvid);
		if(origBean==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
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
	@Resource(name="SegmentvoteMapper")
	public void setbMapper(BeanMapper<Segmentvote,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	private List<List<String>> referencing(Segmentvote bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		if(!((SegmentvoteMapper)bMapper).referencingTvtaid(bean.getTvtaid()))
		{	fields.add(java.util.Arrays.asList("tvtaid"));
		}
		if(!((SegmentvoteMapper)bMapper).referencingTvusid(bean.getTvusid()))
		{	fields.add(java.util.Arrays.asList("tvusid"));
		}
		return fields;
	}
	private List<List<String>> referencedAndchanged(Segmentvote origBean,Segmentvote bean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		return fields;
	}
	private List<List<String>> referenced(Segmentvote origBean)
	{	List<List<String>> fields=new ArrayList<List<String>>();
		return fields;
	}
}