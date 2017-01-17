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
import com.p8499.lang.bean.Wordvote;
import com.p8499.lang.mask.WordvoteMask;
import com.p8499.lang.mapper.WordvoteMapper;

@Controller
@RequestMapping(value="/api/wordvote_attachment",produces="multipart/form-data")
public class WordvoteAttachmentController extends AttachmentControllerBase<Wordvote,Integer>
{	@RequestMapping(value="/{wvid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer wvid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_ra=checkSecurity(session,"wdvt_ra"),wdvt_ri=checkSecurity(session,"wdvt_ri");
		if(!wdvt_ra&&!wdvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		WordvoteMask maskObj=new WordvoteMask().setWvusid(true);
		Wordvote result=((WordvoteMapper)bMapper).get(wvid,maskObj);
		if(!wdvt_ra&&!result.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Wordvote"+"_"+wvid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Wordvote",wvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{wvid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer wvid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_wa=checkSecurity(session,"wdvt_wa"),wdvt_wi=checkSecurity(session,"wdvt_wi");
		if(!wdvt_wa&&!wdvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdvtK"+wvid)&&!reserved.isReservedBy("wdvtK"+wvid,session.getId()))
			return finish("",response,423);
		Wordvote origBean=((WordvoteMapper)bMapper).get(wvid,null);
		if(!wdvt_wa&&!origBean.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Wordvote",wvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{wvid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer wvid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean wdvt_wa=checkSecurity(session,"wdvt_wa"),wdvt_wi=checkSecurity(session,"wdvt_wi");
		if(!wdvt_wa&&!wdvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("wdvtK"+wvid))
			return finish("",response,423);
		Wordvote origBean=((WordvoteMapper)bMapper).get(wvid,null);
		if(!wdvt_wa&&!origBean.getWvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Wordvote",wvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}