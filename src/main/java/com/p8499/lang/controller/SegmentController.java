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
import com.p8499.lang.bean.Segment;
import com.p8499.lang.mask.SegmentMask;
import com.p8499.lang.mapper.SegmentMapper;

@RestController
@RequestMapping(value="/api/segment",produces="application/json;charset=UTF-8")
public class SegmentController extends RestControllerBase<Segment,SegmentMask,Integer>
{	@RequestMapping(value="/{trasid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_ra=checkSecurity(session,"sgmt_ra"),sgmt_ri=checkSecurity(session,"sgmt_ri");
		if(!sgmt_ra&&!sgmt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		Segment result=((SegmentMapper)bMapper).get(trasid);
		if(!sgmt_ra&&!result.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Segment bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgmt_wa)
			bean.setTrusid(getUser(session));
		bean.setTrupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTrupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeAdd(bean);
		((SegmentMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Update.class}) Segment bean,BindingResult result) throws JsonProcessingException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgmtK"+bean.getTrasid())&&!reserved.isReservedBy("sgmtK"+bean.getTrasid(),session.getId()))
			return finish("",response,423);
		Segment origBean=((SegmentMapper)bMapper).get(bean.getTrasid());
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgmt_wa)
			bean.setTrusid(getUser(session));
		bean.setTrupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTrupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		getListener().beforeUpdate(bean);
		((SegmentMapper)bMapper).update(bean);
		getListener().afterUpdate(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgmtK"+trasid))
			return finish("",response,423);
		Segment origBean=((SegmentMapper)bMapper).get(trasid);
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		getListener().beforeDelete(trasid);
		boolean success=((SegmentMapper)bMapper).delete(trasid);
		getListener().afterDelete(trasid);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_ra=checkSecurity(session,"sgmt_ra"),sgmt_ri=checkSecurity(session,"sgmt_ri");
		if(!sgmt_ra&&!sgmt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgmt_ra)
			return finish(queryRange(response,filter,orderBy,range,"trusid",getUser(session)),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="segmentMapper")
	public void setbMapper(BeanMapper<Segment,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="segmentListener")
	public void setListener(BeanListener<Segment,SegmentMask,Integer> listener)
	{	super.setListener(listener);
	}
}