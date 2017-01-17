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
import com.p8499.lang.bean.Usercreation;
import com.p8499.lang.mask.UsercreationMask;
import com.p8499.lang.mapper.UsercreationMapper;

@RestController
@RequestMapping(value="/api/usercreation",produces="application/json;charset=UTF-8")
public class UsercreationController extends RestControllerBase<Usercreation,UsercreationMask,Integer>
{	@RequestMapping(value="/{ucid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_ra=checkSecurity(session,"uscr_ra"),uscr_ri=checkSecurity(session,"uscr_ri");
		if(!uscr_ra&&!uscr_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UsercreationMask maskObj=mask==null?null:(UsercreationMask)jackson.readValue(mask,UsercreationMask.class);
		Usercreation result=((UsercreationMapper)bMapper).get(ucid,maskObj);
		if(!uscr_ra&&!result.getUcusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Usercreation bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_wa=checkSecurity(session,"uscr_wa"),uscr_wi=checkSecurity(session,"uscr_wi");
		if(!uscr_wa&&!uscr_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!uscr_wa)
			bean.setUcusid(getUser(session));
		bean.setUccrdd(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
		bean.setUccrdt(new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((UsercreationMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{ucid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid,@RequestBody @Validated({Update.class}) Usercreation bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	UsercreationMask maskObj=mask==null?null:(UsercreationMask)jackson.readValue(mask,UsercreationMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getUcid()&&result.getFieldErrorCount("ucid")>0||mask!=null&&maskObj.getUccrdd()&&result.getFieldErrorCount("uccrdd")>0||mask!=null&&maskObj.getUccrdt()&&result.getFieldErrorCount("uccrdt")>0||mask!=null&&maskObj.getUcusid()&&result.getFieldErrorCount("ucusid")>0||mask!=null&&maskObj.getUcpv()&&result.getFieldErrorCount("ucpv")>0||mask!=null&&maskObj.getUcmv()&&result.getFieldErrorCount("ucmv")>0||mask!=null&&maskObj.getUcst()&&result.getFieldErrorCount("ucst")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_wa=checkSecurity(session,"uscr_wa"),uscr_wi=checkSecurity(session,"uscr_wi");
		if(!uscr_wa&&!uscr_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("uscrK"+ucid)&&!reserved.isReservedBy("uscrK"+ucid,session.getId()))
			return finish("",response,423);
		Usercreation origBean=((UsercreationMapper)bMapper).get(bean.getUcid(),null);
		if(!uscr_wa&&!origBean.getUcusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!uscr_wa)
			bean.setUcusid(getUser(session));
		bean.setUccrdd(origBean.getUccrdd());
		bean.setUccrdt(origBean.getUccrdt());
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((UsercreationMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{ucid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_wa=checkSecurity(session,"uscr_wa"),uscr_wi=checkSecurity(session,"uscr_wi");
		if(!uscr_wa&&!uscr_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("uscrK"+ucid))
			return finish("",response,423);
		Usercreation origBean=((UsercreationMapper)bMapper).get(ucid,null);
		if(!uscr_wa&&!origBean.getUcusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((UsercreationMapper)bMapper).delete(ucid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_ra=checkSecurity(session,"uscr_ra"),uscr_ri=checkSecurity(session,"uscr_ri");
		if(!uscr_ra&&!uscr_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UsercreationMask maskObj=mask==null?new UsercreationMask().all(true):(UsercreationMask)jackson.readValue(mask,UsercreationMask.class);
		if(!uscr_ra)
			return finish(queryRange(response,filter,orderBy,range,"ucusid",getUser(session),maskObj),response,HttpURLConnection.HTTP_OK);
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
	@Resource(name="usercreationMapper")
	public void setbMapper(BeanMapper<Usercreation,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="usercreationListener")
	public void setListener(BeanListener<Usercreation,UsercreationMask> listener)
	{	super.setListener(listener);
	}
}