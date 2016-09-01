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
import com.p8499.lang.bean.Wordflow;
import com.p8499.lang.mask.WordflowMask;
import com.p8499.lang.mapper.WordflowMapper;

@RestController
@RequestMapping(value="/api/wordflow",produces="application/json;charset=UTF-8")
public class WordflowController extends RestControllerBase<Wordflow,WordflowMask,Integer>
{	@RequestMapping(value="/{waid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_ra=checkSecurity(session,"wdfl_ra"),wdfl_ri=checkSecurity(session,"wdfl_ri");
		if(!wdfl_ra&&!wdfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Wordflow result=((WordflowMapper)bMapper).get(waid);
		if(!wdfl_ra&&!result.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Wordflow bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdfl_wa)
			bean.setWausid(getUser(session));
		if(bean.getWasi()==null)
			bean.setWasi(((WordflowMapper)bMapper).nextWasi(bean.getWawoid()));
		bean.setWacrdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWacrdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		bean.setWaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeAdd(bean);
		((WordflowMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
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
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdfl_wa)
			bean.setWausid(getUser(session));
		bean.setWacrdd(origBean.getWacrdd());
		bean.setWacrdt(origBean.getWacrdt());
		bean.setWaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setWaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((WordflowMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
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
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(waid);
		boolean success=((WordflowMapper)bMapper).delete(waid);
		getListener().afterDelete(waid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_ra=checkSecurity(session,"wdfl_ra"),wdfl_ri=checkSecurity(session,"wdfl_ri");
		if(!wdfl_ra&&!wdfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!wdfl_ra)
			return finish(queryRange(response,filter,orderBy,range,"wausid",getUser(session)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="wordflowMapper")
	public void setbMapper(BeanMapper<Wordflow,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="wordflowListener")
	public void setListener(BeanListener<Wordflow,WordflowMask,Integer> listener)
	{	super.setListener(listener);
	}
}