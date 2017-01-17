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
import com.p8499.lang.bean.Article;
import com.p8499.lang.mask.ArticleMask;
import com.p8499.lang.mapper.ArticleMapper;

@Controller
@RequestMapping(value="/api/article_attachment",produces="multipart/form-data")
public class ArticleAttachmentController extends AttachmentControllerBase<Article,Integer>
{	@RequestMapping(value="/{atid}",method=RequestMethod.GET)
	public String get(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_ra=checkSecurity(session,"artc_ra"),artc_ri=checkSecurity(session,"artc_ri");
		if(!artc_ra&&!artc_ri)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ArticleMask maskObj=new ArticleMask().setAtusid(true);
		Article result=((ArticleMapper)bMapper).get(atid,maskObj);
		if(!artc_ra&&!result.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition","attachment;fileName=Article"+"_"+atid+".txt");
		boolean succ=AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),"Article",atid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_BAD_REQUEST);
	}
	@RequestMapping(value="/{atid}",method=RequestMethod.PUT)
	public String update(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid,@RequestParam MultipartFile file) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("artcK"+atid)&&!reserved.isReservedBy("artcK"+atid,session.getId()))
			return finish("",response,423);
		Article origBean=((ArticleMapper)bMapper).get(atid,null);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.writeFile(file,request.getServletContext().getRealPath(attachmentFolder),"Article",atid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@RequestMapping(value="/{atid}",method=RequestMethod.DELETE)
	public String delete(HttpSession session,HttpServletRequest request,HttpServletResponse response,@PathVariable Integer atid) throws IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		boolean artc_wa=checkSecurity(session,"artc_wa"),artc_wi=checkSecurity(session,"artc_wi");
		if(!artc_wa&&!artc_wi)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(reserved.isReserved("artcK"+atid))
			return finish("",response,423);
		Article origBean=((ArticleMapper)bMapper).get(atid,null);
		if(!artc_wa&&!origBean.getAtusid().equals(getUser(session)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		boolean succ=AttachmentControllerBase.deleteFile(request.getServletContext().getRealPath(attachmentFolder),"Article",atid.toString(),"txt");
		return finish("",response,succ?HttpURLConnection.HTTP_OK:HttpURLConnection.HTTP_NOT_FOUND);
	}
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
}