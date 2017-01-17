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
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.mask.SentenceMask;
import com.p8499.lang.mapper.SentenceMapper;

@Controller
@RequestMapping(value="/api/sentence_attachment",produces="multipart/form-data")
public class SentenceAttachmentController extends AttachmentControllerBase<Sentence,Integer>
{	@RequestMapping(value="/{asid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_ra=checkSecurity(session,"sent_ra"),sent_ri=checkSecurity(session,"sent_ri");
		if(!sent_ra&&!sent_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SentenceMask maskObj=new SentenceMask().setAsusid(true);
		Sentence result=((SentenceMapper)bMapper).get(asid,maskObj);
		if(!sent_ra&&!result.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Sentence"+"_"+asid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Sentence",asid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{asid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sentK"+asid)&&!reserved.isReservedBy("sentK"+asid,session.getId()))
			return finish("",response,423);
		Sentence origBean=((SentenceMapper)bMapper).get(asid,null);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Sentence",asid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{asid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer asid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sent_wa=checkSecurity(session,"sent_wa"),sent_wi=checkSecurity(session,"sent_wi");
		if(!sent_wa&&!sent_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sentK"+asid))
			return finish("",response,423);
		Sentence origBean=((SentenceMapper)bMapper).get(asid,null);
		if(!sent_wa&&!origBean.getAsusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Sentence",asid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}