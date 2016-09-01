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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.mapper.SegmentvoteMapper;
import com.p8499.lang.mask.SegmentvoteMask;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.MaskControllerBase;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.mvc.database.Update;

@RestController
@RequestMapping(value="/api/segmentvote_mask",produces="application/json;charset=UTF-8")
public class SegmentvoteMaskController extends MaskControllerBase<Segmentvote,SegmentvoteMask,Integer>
{	@RequestMapping(value="/{tvid}",method=RequestMethod.GET)
	public String getWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_ra=checkSecurity(session,"sgvt_ra"),sgvt_ri=checkSecurity(session,"sgvt_ri");
		if(!sgvt_ra&&!sgvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Segmentvote result=((SegmentvoteMapper)bMapper).getWithMask(tvid,(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class));
		if(!sgvt_ra&&!result.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.PUT)
	public String updateWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Segmentvote bean,BindingResult result,@RequestParam("mask")String mask) throws IOException
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
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgvt_wa)
			bean.setTvusid(getUser(session));
		bean.setTaupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTaupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		SegmentvoteMask maskObj=(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class);
		getListener().beforeUpdateWithMask(bean,maskObj);
		((SegmentvoteMapper)bMapper).updateWithMask(bean,maskObj);
		getListener().afterUpdateWithMask(bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String queryWithMask(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam("mask")String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_ra=checkSecurity(session,"sgvt_ra"),sgvt_ri=checkSecurity(session,"sgvt_ri");
		if(!sgvt_ra&&!sgvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgvt_ra)
			return finish(queryRange(response,filter,orderBy,range,"tvusid",getUser(session),(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class)),response,HttpURLConnection.HTTP_OK);
		return finish(queryRange(response,filter,orderBy,range,(SegmentvoteMask)jackson.readValue(mask,SegmentvoteMask.class)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="segmentvoteListener")
	public void setListener(BeanListener<Segmentvote,SegmentvoteMask,Integer> listener)
	{	super.setListener(listener);
	}
}