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
import com.p8499.lang.bean.Wordflow;
import com.p8499.lang.mask.WordflowMask;
import com.p8499.lang.mapper.WordflowMapper;

@Controller
@RequestMapping(value="/api/wordflow_attachment",produces="multipart/form-data")
public class WordflowAttachmentController extends AttachmentControllerBase<Wordflow,Integer>
{	@RequestMapping(value="/{waid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_ra=checkSecurity(session,"wdfl_ra"),wdfl_ri=checkSecurity(session,"wdfl_ri");
		if(!wdfl_ra&&!wdfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordflowMask maskObj=new WordflowMask().setWausid(true);
		Wordflow result=((WordflowMapper)bMapper).get(waid,maskObj);
		if(!wdfl_ra&&!result.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Wordflow"+"_"+waid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Wordflow",waid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+waid)&&!reserved.isReservedBy("wdflK"+waid,session.getId()))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(waid,null);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Wordflow",waid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{waid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer waid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdfl_wa=checkSecurity(session,"wdfl_wa"),wdfl_wi=checkSecurity(session,"wdfl_wi");
		if(!wdfl_wa&&!wdfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdflK"+waid))
			return finish("",response,423);
		Wordflow origBean=((WordflowMapper)bMapper).get(waid,null);
		if(!wdfl_wa&&!origBean.getWausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Wordflow",waid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}