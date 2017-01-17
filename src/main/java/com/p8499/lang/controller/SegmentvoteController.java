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
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.mask.SegmentvoteMask;
import com.p8499.lang.mapper.SegmentvoteMapper;

@RestController
@RequestMapping(value="/api/segmentvote",produces="application/json;charset=UTF-8")
public class SegmentvoteController extends RestControllerBase<Segmentvote,SegmentvoteMask,Integer>
{	@RequestMapping(value="/{tvid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_ra=checkSecurity(session,"sgvt_ra"),sgvt_ri=checkSecurity(session,"sgvt_ri");
		if(!sgvt_ra&&!sgvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentvoteMask maskObj=mask==null?null:(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class);
		Segmentvote result=((SegmentvoteMapper)bMapper).get(tvid,maskObj);
		if(!sgvt_ra&&!result.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Segmentvote bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgvt_wa)
			bean.setTvusid(getUser(session));
		if(bean.getTvsi()==null)
			bean.setTvsi(((SegmentvoteMapper)bMapper).nextTvsi(bean.getTvtaid()));
		bean.setTvupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTvupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentvoteMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid,@RequestBody @Validated({Update.class}) Segmentvote bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	SegmentvoteMask maskObj=mask==null?null:(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getTvid()&&result.getFieldErrorCount("tvid")>0||mask!=null&&maskObj.getTvtaid()&&result.getFieldErrorCount("tvtaid")>0||mask!=null&&maskObj.getTvsi()&&result.getFieldErrorCount("tvsi")>0||mask!=null&&maskObj.getTvusid()&&result.getFieldErrorCount("tvusid")>0||mask!=null&&maskObj.getTvpo()&&result.getFieldErrorCount("tvpo")>0||mask!=null&&maskObj.getTvupdd()&&result.getFieldErrorCount("tvupdd")>0||mask!=null&&maskObj.getTvupdt()&&result.getFieldErrorCount("tvupdt")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgvtK"+tvid)&&!reserved.isReservedBy("sgvtK"+tvid,session.getId()))
			return finish("",response,423);
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(bean.getTvid(),null);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgvt_wa)
			bean.setTvusid(getUser(session));
		bean.setTvupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTvupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentvoteMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
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
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(tvid,null);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((SegmentvoteMapper)bMapper).delete(tvid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_ra=checkSecurity(session,"sgvt_ra"),sgvt_ri=checkSecurity(session,"sgvt_ri");
		if(!sgvt_ra&&!sgvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentvoteMask maskObj=mask==null?new SegmentvoteMask().all(true):(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class);
		if(!sgvt_ra)
			return finish(queryRange(response,filter,orderBy,range,"tvusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="segmentvoteMapper")
	public void setbMapper(BeanMapper<Segmentvote,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="segmentvoteListener")
	public void setListener(BeanListener<Segmentvote,SegmentvoteMask> listener)
	{	super.setListener(listener);
	}
}