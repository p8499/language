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
import com.p8499.lang.bean.Segmentflow;
import com.p8499.lang.mask.SegmentflowMask;
import com.p8499.lang.mapper.SegmentflowMapper;

@Controller
@RequestMapping(value="/api/segmentflow_attachment",produces="multipart/form-data")
public class SegmentflowAttachmentController extends AttachmentControllerBase<Segmentflow,Integer>
{	@RequestMapping(value="/{taid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_ra=checkSecurity(session,"sgfl_ra"),sgfl_ri=checkSecurity(session,"sgfl_ri");
		if(!sgfl_ra&&!sgfl_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentflowMask maskObj=new SegmentflowMask().setTausid(true);
		Segmentflow result=((SegmentflowMapper)bMapper).get(taid,maskObj);
		if(!sgfl_ra&&!result.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Segmentflow"+"_"+taid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Segmentflow",taid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{taid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_wa=checkSecurity(session,"sgfl_wa"),sgfl_wi=checkSecurity(session,"sgfl_wi");
		if(!sgfl_wa&&!sgfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgflK"+taid)&&!reserved.isReservedBy("sgflK"+taid,session.getId()))
			return finish("",response,423);
		Segmentflow origBean=((SegmentflowMapper)bMapper).get(taid,null);
		if(!sgfl_wa&&!origBean.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Segmentflow",taid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{taid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer taid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgfl_wa=checkSecurity(session,"sgfl_wa"),sgfl_wi=checkSecurity(session,"sgfl_wi");
		if(!sgfl_wa&&!sgfl_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgflK"+taid))
			return finish("",response,423);
		Segmentflow origBean=((SegmentflowMapper)bMapper).get(taid,null);
		if(!sgfl_wa&&!origBean.getTausid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Segmentflow",taid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}