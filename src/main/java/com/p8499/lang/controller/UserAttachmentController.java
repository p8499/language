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
import com.p8499.lang.bean.User;
import com.p8499.lang.mask.UserMask;
import com.p8499.lang.mapper.UserMapper;

@Controller
@RequestMapping(value="/api/user_attachment",produces="multipart/form-data")
public class UserAttachmentController extends AttachmentControllerBase<User,String>
{	@RequestMapping(value="/{usid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_ra=checkSecurity(session,"user_ra"),user_ri=checkSecurity(session,"user_ri");
		if(!user_ra&&!user_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UserMask maskObj=new UserMask().setUsid(true);
		User result=((UserMapper)bMapper).get(usid,maskObj);
		if(!user_ra&&!result.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=User"+"_"+usid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"User",usid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+usid)&&!reserved.isReservedBy("userK"+usid,session.getId()))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(usid,null);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"User",usid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{usid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable String usid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean user_wa=checkSecurity(session,"user_wa"),user_wi=checkSecurity(session,"user_wi");
		if(!user_wa&&!user_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("userK"+usid))
			return finish("",response,423);
		User origBean=((UserMapper)bMapper).get(usid,null);
		if(!user_wa&&!origBean.getUsid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"User",usid,"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}