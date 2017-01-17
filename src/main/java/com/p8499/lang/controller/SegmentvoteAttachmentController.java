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
import com.p8499.lang.bean.Segmentvote;
import com.p8499.lang.mask.SegmentvoteMask;
import com.p8499.lang.mapper.SegmentvoteMapper;

@Controller
@RequestMapping(value="/api/segmentvote_attachment",produces="multipart/form-data")
public class SegmentvoteAttachmentController extends AttachmentControllerBase<Segmentvote,Integer>
{	@RequestMapping(value="/{tvid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_ra=checkSecurity(session,"sgvt_ra"),sgvt_ri=checkSecurity(session,"sgvt_ri");
		if(!sgvt_ra&&!sgvt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentvoteMask maskObj=new SegmentvoteMask().setTvusid(true);
		Segmentvote result=((SegmentvoteMapper)bMapper).get(tvid,maskObj);
		if(!sgvt_ra&&!result.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Segmentvote"+"_"+tvid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Segmentvote",tvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgvtK"+tvid)&&!reserved.isReservedBy("sgvtK"+tvid,session.getId()))
			return finish("",response,423);
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(tvid,null);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Segmentvote",tvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{tvid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer tvid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgvt_wa=checkSecurity(session,"sgvt_wa"),sgvt_wi=checkSecurity(session,"sgvt_wi");
		if(!sgvt_wa&&!sgvt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgvtK"+tvid))
			return finish("",response,423);
		Segmentvote origBean=((SegmentvoteMapper)bMapper).get(tvid,null);
		if(!sgvt_wa&&!origBean.getTvusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Segmentvote",tvid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}