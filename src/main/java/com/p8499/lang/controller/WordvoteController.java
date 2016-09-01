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
import com.p8499.lang.bean.Wordvote;
import com.p8499.lang.mask.WordvoteMask;
import com.p8499.lang.mapper.WordvoteMapper;

@RestController
@RequestMapping(value="/api/wordvote",produces="application/json;charset=UTF-8")
public class WordvoteController extends RestControllerBase<Wordvote,WordvoteMask,Integer>
{	@RequestMapping(value="/{wvid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer wvid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_ra=checkSecurity(session,"wdvt_ra"),wdvt_ri=checkSecurity(session,"wdvt_ri");
		if(!wdvt_ra&&!wdvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Wordvote result=((WordvoteMapper)bMapper).get(wvid);
		if(!wdvt_ra&&!result.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Wordvote bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_wa=checkSecurity(session,"wdvt_wa"),wdvt_wi=checkSecurity(session,"wdvt_wi");
		if(!wdvt_wa&&!wdvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdvt_wa)
			bean.setWvusid(getUser(session));
		if(bean.getWvsi()==null)
			bean.setWvsi(((WordvoteMapper)bMapper).nextWvsi(bean.getWvwaid()));
		bean.setWvupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWvupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeAdd(bean);
		((WordvoteMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{wvid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Wordvote bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_wa=checkSecurity(session,"wdvt_wa"),wdvt_wi=checkSecurity(session,"wdvt_wi");
		if(!wdvt_wa&&!wdvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdvtK"+bean.getWvid())&&!reserved.isReservedBy("wdvtK"+bean.getWvid(),session.getId()))
			return finish("",response,423);
		Wordvote origBean=((WordvoteMapper)bMapper).get(bean.getWvid());
		if(!wdvt_wa&&!origBean.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdvt_wa)
			bean.setWvusid(getUser(session));
		bean.setWvupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWvupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((WordvoteMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{wvid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer wvid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_wa=checkSecurity(session,"wdvt_wa"),wdvt_wi=checkSecurity(session,"wdvt_wi");
		if(!wdvt_wa&&!wdvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdvtK"+wvid))
			return finish("",response,423);
		Wordvote origBean=((WordvoteMapper)bMapper).get(wvid);
		if(!wdvt_wa&&!origBean.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(wvid);
		boolean success=((WordvoteMapper)bMapper).delete(wvid);
		getListener().afterDelete(wvid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_ra=checkSecurity(session,"wdvt_ra"),wdvt_ri=checkSecurity(session,"wdvt_ri");
		if(!wdvt_ra&&!wdvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdvt_ra)
			return finish(queryRange(response,filter,orderBy,range,"wvusid",getUser(session)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="wordvoteMapper")
	public void setbMapper(BeanMapper<Wordvote,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="wordvoteListener")
	public void setListener(BeanListener<Wordvote,WordvoteMask,Integer> listener)
	{	super.setListener(listener);
	}
}