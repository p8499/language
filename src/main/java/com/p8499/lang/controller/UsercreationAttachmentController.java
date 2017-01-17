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
import com.p8499.lang.bean.Usercreation;
import com.p8499.lang.mask.UsercreationMask;
import com.p8499.lang.mapper.UsercreationMapper;

@Controller
@RequestMapping(value="/api/usercreation_attachment",produces="multipart/form-data")
public class UsercreationAttachmentController extends AttachmentControllerBase<Usercreation,Integer>
{	@RequestMapping(value="/{ucid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_ra=checkSecurity(session,"uscr_ra"),uscr_ri=checkSecurity(session,"uscr_ri");
		if(!uscr_ra&&!uscr_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		UsercreationMask maskObj=new UsercreationMask().setUcusid(true);
		Usercreation result=((UsercreationMapper)bMapper).get(ucid,maskObj);
		if(!uscr_ra&&!result.getUcusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Usercreation"+"_"+ucid+".png");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Usercreation",ucid.toString(),"png");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{ucid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean uscr_wa=checkSecurity(session,"uscr_wa"),uscr_wi=checkSecurity(session,"uscr_wi");
		if(!uscr_wa&&!uscr_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("uscrK"+ucid)&&!reserved.isReservedBy("uscrK"+ucid,session.getId()))
			return finish("",response,423);
		Usercreation origBean=((UsercreationMapper)bMapper).get(ucid,null);
		if(!uscr_wa&&!origBean.getUcusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Usercreation",ucid.toString(),"png");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{ucid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer ucid) throws IOException
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
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Usercreation",ucid.toString(),"png");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}