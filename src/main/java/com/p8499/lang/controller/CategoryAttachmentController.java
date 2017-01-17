package com.p8499.lang.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.p8499.mvc.AttachmentControllerBase;
import com.p8499.lang.bean.Category;

@Controller
@RequestMapping(value="/api/category_attachment",produces="multipart/form-data")
public class CategoryAttachmentController extends AttachmentControllerBase<Category,Integer>
{	@RequestMapping(value="/{cgid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_ra=checkSecurity(session,"cate_ra");
		if(!cate_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Category"+"_"+cgid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Category",cgid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{cgid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_wa=checkSecurity(session,"cate_wa");
		if(!cate_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("cateK"+cgid)&&!reserved.isReservedBy("cateK"+cgid,session.getId()))
			return finish("",response,423);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Category",cgid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{cgid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer cgid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean cate_wa=checkSecurity(session,"cate_wa");
		if(!cate_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("cateK"+cgid))
			return finish("",response,423);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Category",cgid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}