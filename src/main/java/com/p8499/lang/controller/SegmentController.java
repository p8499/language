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
import com.p8499.lang.bean.Segment;
import com.p8499.lang.mask.SegmentMask;
import com.p8499.lang.mapper.SegmentMapper;

@RestController
@RequestMapping(value="/api/segment",produces="application/json;charset=UTF-8")
public class SegmentController extends RestControllerBase<Segment,SegmentMask,Integer>
{	@RequestMapping(value="/{trasid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_ra=checkSecurity(session,"sgmt_ra"),sgmt_ri=checkSecurity(session,"sgmt_ri");
		if(!sgmt_ra&&!sgmt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentMask maskObj=mask==null?null:(SegmentMask)jackson.readValue(mask,SegmentMask.class);
		Segment result=((SegmentMapper)bMapper).get(trasid,maskObj);
		if(!sgmt_ra&&!result.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Segment bean,BindingResult result) throws IllegalStateException, IOException
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
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid,@RequestBody @Validated({Update.class}) Segment bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	SegmentMask maskObj=mask==null?null:(SegmentMask)jackson.readValue(mask,SegmentMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getTrasid()&&result.getFieldErrorCount("trasid")>0||mask!=null&&maskObj.getTrpi()&&result.getFieldErrorCount("trpi")>0||mask!=null&&maskObj.getTrhz()&&result.getFieldErrorCount("trhz")>0||mask!=null&&maskObj.getTrst()&&result.getFieldErrorCount("trst")>0||mask!=null&&maskObj.getTrusid()&&result.getFieldErrorCount("trusid")>0||mask!=null&&maskObj.getTrupdd()&&result.getFieldErrorCount("trupdd")>0||mask!=null&&maskObj.getTrupdt()&&result.getFieldErrorCount("trupdt")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgmtK"+trasid)&&!reserved.isReservedBy("sgmtK"+trasid,session.getId()))
			return finish("",response,423);
		Segment origBean=((SegmentMapper)bMapper).get(bean.getTrasid(),null);
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!sgmt_wa)
			bean.setTrusid(getUser(session));
		bean.setTrupdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setTrupdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((SegmentMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
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
		Segment origBean=((SegmentMapper)bMapper).get(trasid,null);
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((SegmentMapper)bMapper).delete(trasid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_ra=checkSecurity(session,"sgmt_ra"),sgmt_ri=checkSecurity(session,"sgmt_ri");
		if(!sgmt_ra&&!sgmt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentMask maskObj=mask==null?new SegmentMask().all(true):(SegmentMask)jackson.readValue(mask,SegmentMask.class);
		if(!sgmt_ra)
			return finish(queryRange(response,filter,orderBy,range,"trusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="segmentMapper")
	public void setbMapper(BeanMapper<Segment,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="segmentListener")
	public void setListener(BeanListener<Segment,SegmentMask> listener)
	{	super.setListener(listener);
	}
}