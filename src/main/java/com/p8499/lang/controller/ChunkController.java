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
import com.p8499.lang.bean.Chunk;
import com.p8499.lang.mask.ChunkMask;
import com.p8499.lang.mapper.ChunkMapper;

@RestController
@RequestMapping(value="/api/chunk",produces="application/json;charset=UTF-8")
public class ChunkController extends RestControllerBase<Chunk,ChunkMask,Integer>
{	@RequestMapping(value="/{cpid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cpid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean chnk_ra=checkSecurity(session,"chnk_ra");
		if(!chnk_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ChunkMask maskObj=mask==null?null:(ChunkMask)jackson.readValue(mask,ChunkMask.class);
		Chunk result=((ChunkMapper)bMapper).get(cpid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Chunk bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean chnk_wa=checkSecurity(session,"chnk_wa");
		if(!chnk_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ChunkMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{cpid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cpid,@RequestBody @Validated({Update.class}) Chunk bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	ChunkMask maskObj=mask==null?null:(ChunkMask)jackson.readValue(mask,ChunkMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getCpid()&&result.getFieldErrorCount("cpid")>0||mask!=null&&maskObj.getCplsid()&&result.getFieldErrorCount("cplsid")>0||mask!=null&&maskObj.getCpsi()&&result.getFieldErrorCount("cpsi")>0||mask!=null&&maskObj.getCptg()&&result.getFieldErrorCount("cptg")>0||mask!=null&&maskObj.getCpft()&&result.getFieldErrorCount("cpft")>0||mask!=null&&maskObj.getCpsort()&&result.getFieldErrorCount("cpsort")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean chnk_wa=checkSecurity(session,"chnk_wa");
		if(!chnk_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("chnkK"+cpid)&&!reserved.isReservedBy("chnkK"+cpid,session.getId()))
			return finish("",response,423);
		Chunk origBean=((ChunkMapper)bMapper).get(bean.getCpid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ChunkMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{cpid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cpid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean chnk_wa=checkSecurity(session,"chnk_wa");
		if(!chnk_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("chnkK"+cpid))
			return finish("",response,423);
		Chunk origBean=((ChunkMapper)bMapper).get(cpid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((ChunkMapper)bMapper).delete(cpid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean chnk_ra=checkSecurity(session,"chnk_ra");
		if(!chnk_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ChunkMask maskObj=mask==null?new ChunkMask().all(true):(ChunkMask)jackson.readValue(mask,ChunkMask.class);
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
	@Resource(name="chunkMapper")
	public void setbMapper(BeanMapper<Chunk,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="chunkListener")
	public void setListener(BeanListener<Chunk,ChunkMask> listener)
	{	super.setListener(listener);
	}
}