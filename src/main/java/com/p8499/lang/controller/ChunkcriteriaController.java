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
import com.p8499.lang.bean.Chunkcriteria;
import com.p8499.lang.mask.ChunkcriteriaMask;
import com.p8499.lang.mapper.ChunkcriteriaMapper;

@RestController
@RequestMapping(value="/api/chunkcriteria",produces="application/json;charset=UTF-8")
public class ChunkcriteriaController extends RestControllerBase<Chunkcriteria,ChunkcriteriaMask,Integer>
{	@RequestMapping(value="/{ccid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ccid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean ckct_ra=checkSecurity(session,"ckct_ra");
		if(!ckct_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ChunkcriteriaMask maskObj=mask==null?null:(ChunkcriteriaMask)jackson.readValue(mask,ChunkcriteriaMask.class);
		Chunkcriteria result=((ChunkcriteriaMapper)bMapper).get(ccid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Chunkcriteria bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean ckct_wa=checkSecurity(session,"ckct_wa");
		if(!ckct_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(bean.getCcsi()==null)
			bean.setCcsi(((ChunkcriteriaMapper)bMapper).nextCcsi(bean.getCccpid()));
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ChunkcriteriaMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{ccid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ccid,@RequestBody @Validated({Update.class}) Chunkcriteria bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	ChunkcriteriaMask maskObj=mask==null?null:(ChunkcriteriaMask)jackson.readValue(mask,ChunkcriteriaMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getCcid()&&result.getFieldErrorCount("ccid")>0||mask!=null&&maskObj.getCccpid()&&result.getFieldErrorCount("cccpid")>0||mask!=null&&maskObj.getCcsi()&&result.getFieldErrorCount("ccsi")>0||mask!=null&&maskObj.getCctk()&&result.getFieldErrorCount("cctk")>0||mask!=null&&maskObj.getCctg()&&result.getFieldErrorCount("cctg")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean ckct_wa=checkSecurity(session,"ckct_wa");
		if(!ckct_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("ckctK"+ccid)&&!reserved.isReservedBy("ckctK"+ccid,session.getId()))
			return finish("",response,423);
		Chunkcriteria origBean=((ChunkcriteriaMapper)bMapper).get(bean.getCcid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((ChunkcriteriaMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{ccid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ccid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean ckct_wa=checkSecurity(session,"ckct_wa");
		if(!ckct_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("ckctK"+ccid))
			return finish("",response,423);
		Chunkcriteria origBean=((ChunkcriteriaMapper)bMapper).get(ccid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((ChunkcriteriaMapper)bMapper).delete(ccid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean ckct_ra=checkSecurity(session,"ckct_ra");
		if(!ckct_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ChunkcriteriaMask maskObj=mask==null?new ChunkcriteriaMask().all(true):(ChunkcriteriaMask)jackson.readValue(mask,ChunkcriteriaMask.class);
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
	@Resource(name="chunkcriteriaMapper")
	public void setbMapper(BeanMapper<Chunkcriteria,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="chunkcriteriaListener")
	public void setListener(BeanListener<Chunkcriteria,ChunkcriteriaMask> listener)
	{	super.setListener(listener);
	}
}