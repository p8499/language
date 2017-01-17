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
import com.p8499.lang.bean.Tagging;
import com.p8499.lang.mask.TaggingMask;
import com.p8499.lang.mapper.TaggingMapper;

@Controller
@RequestMapping(value="/api/tagging_attachment",produces="multipart/form-data")
public class TaggingAttachmentController extends AttachmentControllerBase<Tagging,Integer>
{	@RequestMapping(value="/{tgasid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_ra=checkSecurity(session,"post_ra"),post_ri=checkSecurity(session,"post_ri");
		if(!post_ra&&!post_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		TaggingMask maskObj=new TaggingMask().setTgusid(true);
		Tagging result=((TaggingMapper)bMapper).get(tgasid,maskObj);
		if(!post_ra&&!result.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Tagging"+"_"+tgasid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Tagging",tgasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("postK"+tgasid)&&!reserved.isReservedBy("postK"+tgasid,session.getId()))
			return finish("",response,423);
		Tagging origBean=((TaggingMapper)bMapper).get(tgasid,null);
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Tagging",tgasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{tgasid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tgasid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean post_wa=checkSecurity(session,"post_wa"),post_wi=checkSecurity(session,"post_wi");
		if(!post_wa&&!post_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("postK"+tgasid))
			return finish("",response,423);
		Tagging origBean=((TaggingMapper)bMapper).get(tgasid,null);
		if(!post_wa&&!origBean.getTgusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Tagging",tgasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}