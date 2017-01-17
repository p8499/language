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
import com.p8499.lang.bean.Category;
import com.p8499.lang.mask.CategoryMask;
import com.p8499.lang.mapper.CategoryMapper;

@RestController
@RequestMapping(value="/api/category",produces="application/json;charset=UTF-8")
public class CategoryController extends RestControllerBase<Category,CategoryMask,Integer>
{	@RequestMapping(value="/{cgid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_ra=checkSecurity(session,"cate_ra");
		if(!cate_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		CategoryMask maskObj=mask==null?null:(CategoryMask)jackson.readValue(mask,CategoryMask.class);
		Category result=((CategoryMapper)bMapper).get(cgid,maskObj);
		return finish(result==null?"":result,response,result==null?HttpURLConnection.HTTP_NOT_FOUND:HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(method=RequestMethod.POST)
	public String add(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestBody @Validated({Add.class}) Category bean,BindingResult result) throws IllegalStateException, IOException
	{	if(result.hasErrors())
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_wa=checkSecurity(session,"cate_wa");
		if(!cate_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!getListener().beforeAdd(bean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((CategoryMapper)bMapper).add(bean);
		getListener().afterAdd(bean);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{cgid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid,@RequestBody @Validated({Update.class}) Category bean,BindingResult result,@RequestParam(required=false)String mask) throws IOException
	{	CategoryMask maskObj=mask==null?null:(CategoryMask)jackson.readValue(mask,CategoryMask.class);
		if(mask==null&&result.hasErrors()||mask!=null&&maskObj.getCgid()&&result.getFieldErrorCount("cgid")>0||mask!=null&&maskObj.getCglsid()&&result.getFieldErrorCount("cglsid")>0||mask!=null&&maskObj.getCgsi()&&result.getFieldErrorCount("cgsi")>0||mask!=null&&maskObj.getCgpsi()&&result.getFieldErrorCount("cgpsi")>0||mask!=null&&maskObj.getCgname()&&result.getFieldErrorCount("cgname")>0)
			return finish(result.toString(),response,HttpURLConnection.HTTP_BAD_REQUEST);
		if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_wa=checkSecurity(session,"cate_wa");
		if(!cate_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("cateK"+cgid)&&!reserved.isReservedBy("cateK"+cgid,session.getId()))
			return finish("",response,423);
		Category origBean=((CategoryMapper)bMapper).get(bean.getCgid(),null);
		if(!getListener().beforeUpdate(origBean,bean,maskObj))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		((CategoryMapper)bMapper).update(bean,maskObj);
		getListener().afterUpdate(origBean,bean,maskObj);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/{cgid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_wa=checkSecurity(session,"cate_wa");
		if(!cate_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("cateK"+cgid))
			return finish("",response,423);
		Category origBean=((CategoryMapper)bMapper).get(cgid,null);
		if(!getListener().beforeDelete(origBean))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		boolean success=((CategoryMapper)bMapper).delete(cgid);
		getListener().afterDelete(origBean);
		return finish("",response,success?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NO_CONTENT);
	}
	@RequestMapping(method=RequestMethod.GET)
	public String query(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filter,@RequestParam(required=false) String orderBy,@RequestHeader(required=false,name="Range",defaultValue="items=0-9") String range,@RequestParam(required=false)String mask) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_ra=checkSecurity(session,"cate_ra");
		if(!cate_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		CategoryMask maskObj=mask==null?new CategoryMask().all(true):(CategoryMask)jackson.readValue(mask,CategoryMask.class);
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
	@Resource(name="categoryMapper")
	public void setbMapper(BeanMapper<Category,Integer> bMapper)
	{	super.setbMapper(bMapper);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="categoryListener")
	public void setListener(BeanListener<Category,CategoryMask> listener)
	{	super.setListener(listener);
	}
}