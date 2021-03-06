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
import com.p8499.lang.bean.Language;

@Controller
@RequestMapping(value="/api/language_attachment",produces="multipart/form-data")
public class LanguageAttachmentController extends AttachmentControllerBase<Language,String>
{	@RequestMapping(value="/{lsid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lsid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_ra=checkSecurity(session,"lang_ra");
		if(!lang_ra)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Language"+"_"+lsid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Language",lsid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{lsid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lsid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+lsid)&&!reserved.isReservedBy("langK"+lsid,session.getId()))
			return finish("",response,423);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Language",lsid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{lsid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String lsid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean lang_wa=checkSecurity(session,"lang_wa");
		if(!lang_wa)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("langK"+lsid))
			return finish("",response,423);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Language",lsid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}