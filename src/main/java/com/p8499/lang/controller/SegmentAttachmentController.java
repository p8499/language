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
import com.p8499.lang.bean.Segment;
import com.p8499.lang.mask.SegmentMask;
import com.p8499.lang.mapper.SegmentMapper;

@Controller
@RequestMapping(value="/api/segment_attachment",produces="multipart/form-data")
public class SegmentAttachmentController extends AttachmentControllerBase<Segment,Integer>
{	@RequestMapping(value="/{trasid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_ra=checkSecurity(session,"sgmt_ra"),sgmt_ri=checkSecurity(session,"sgmt_ri");
		if(!sgmt_ra&&!sgmt_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		SegmentMask maskObj=new SegmentMask().setTrusid(true);
		Segment result=((SegmentMapper)bMapper).get(trasid,maskObj);
		if(!sgmt_ra&&!result.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Segment"+"_"+trasid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Segment",trasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgmtK"+trasid)&&!reserved.isReservedBy("sgmtK"+trasid,session.getId()))
			return finish("",response,423);
		Segment origBean=((SegmentMapper)bMapper).get(trasid,null);
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Segment",trasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{trasid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer trasid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean sgmt_wa=checkSecurity(session,"sgmt_wa"),sgmt_wi=checkSecurity(session,"sgmt_wi");
		if(!sgmt_wa&&!sgmt_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("sgmtK"+trasid))
			return finish("",response,423);
		Segment origBean=((SegmentMapper)bMapper).get(trasid,null);
		if(!sgmt_wa&&!origBean.getTrusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Segment",trasid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}