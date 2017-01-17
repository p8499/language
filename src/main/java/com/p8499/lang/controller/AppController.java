package com.p8499.lang.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p8499.mvc.AttachmentControllerBase;
import com.p8499.mvc.ControllerBase;
import com.p8499.mvc.FilterConditionExpr;
import com.p8499.mvc.FilterLogicExpr;
import com.p8499.mvc.MD5Encryptor;
import com.p8499.mvc.Reserved;
import com.p8499.mvc.database.ToolMapper;
import com.p8499.lang.bean.User;
import com.p8499.lang.bean.Usercreation;
import com.p8499.lang.bean.Userrole;
import com.p8499.lang.mapper.UserMapper;
import com.p8499.lang.mapper.UsercreationMapper;
import com.p8499.lang.mapper.UserroleMapper;
import com.p8499.lang.mask.UsercreationMask;

@RestController
@RequestMapping(value="/api/base",produces="application/json;charset=UTF-8")
public class AppController extends ControllerBase
{	@RequestMapping(value="/ucinitialize")
	public String ucinitialize(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String ucusid) throws Exception// throws CaptchaServiceException, IOException
	{	Date now=new Date();
		if(userMapper.exists(ucusid))
			return finish("",response,HttpURLConnection.HTTP_CONFLICT);
		List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucusid,Usercreation.UCST_SENT,dateFormat.format(now)).toString(),null,0,4,new UsercreationMask().setUcid(true));
		if(ucList.size()>=3)
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		ByteArrayOutputStream byteArrayOs=new ByteArrayOutputStream();
		String pCode=EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService,"png",byteArrayOs);
		byte[] bytes=byteArrayOs.toByteArray();
		
		Usercreation uc=new Usercreation().setUccrdd(dateFormat.format(now)).setUccrdt(timeFormat.format(now)).setUcusid(ucusid).setUcpv(pCode).setUcmv("").setUcst(Usercreation.UCST_INITIAL);
		usercreationMapper.add(uc);
		AttachmentControllerBase.writeFile(bytes,request.getServletContext().getRealPath(attachmentFolder),Usercreation.NAME,String.valueOf(uc.getUcid()),"png");
		
		return finish(uc.getUcid(),response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/ucimage",produces="image/png")
	public String ucimage(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam Integer ucid) throws IOException
	{	List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucid,Usercreation.UCST_INITIAL).toString(),null,0,1,new UsercreationMask().setUcid(true));
		if(ucList.isEmpty())
			return finish("",response,HttpURLConnection.HTTP_NOT_FOUND);
		response.setHeader("Content-Type","image/png");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires",0);
		AttachmentControllerBase.readFile(response,request.getServletContext().getRealPath(attachmentFolder),Usercreation.NAME,String.valueOf(ucid),"png");
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/ucpcode")
	public String ucpcode(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam Integer ucid,@RequestParam String pcode) throws JsonProcessingException
	{	List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucid,Usercreation.UCST_INITIAL).toString(),null,0,1,new UsercreationMask().setUcid(true).setUcpv(true));
		if(ucList.isEmpty()||!ucList.get(0).getUcpv().equals(pcode))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/ucwait")
	public String ucwait(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam Integer ucid,@RequestParam String pcode) throws JsonProcessingException
	{	List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucid,Usercreation.UCST_INITIAL).toString(),null,0,1,new UsercreationMask().setUcid(true).setUcpv(true));
		if(ucList.isEmpty()||!ucList.get(0).getUcpv().equals(pcode))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		usercreationMapper.update(ucList.get(0).setUcst(Usercreation.UCST_WAITING),new UsercreationMask().setUcst(true));
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/ucmcode")
	public String ucmcode(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam Integer ucid,@RequestParam String mcode) throws JsonProcessingException
	{	List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucid,Usercreation.UCST_SENT).toString(),null,0,1,new UsercreationMask().setUcid(true).setUcusid(true).setUcmv(true));
		if(ucList.isEmpty()||!ucList.get(0).getUcmv().equals(mcode))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/ucfinalize")
	public String ucfinalize(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam Integer ucid,@RequestParam String mcode,@RequestParam String uspswd) throws JsonProcessingException, UnsupportedEncodingException
	{	//if true,user insert
		List<Usercreation> ucList=usercreationMapper.query(filterUsercreation(ucid,Usercreation.UCST_SENT).toString(),null,0,1,new UsercreationMask().setUcid(true).setUcusid(true).setUcmv(true));
		if(ucList.isEmpty()||!ucList.get(0).getUcmv().equals(mcode))
			return finish("",response,HttpURLConnection.HTTP_NOT_ACCEPTABLE);
		Usercreation uc=ucList.get(0);
		usercreationMapper.update(uc.setUcst(Usercreation.UCST_FINALIZED),new UsercreationMask().setUcst(true));
		userMapper.add(new User().setUsid(uc.getUcusid()).setUspswd(encryptor.entrypt(uspswd)).setUsst(User.USST_ENABLED).setUsname("").setUslsid("wuu-sha").setUspn(1));
		userroleMapper.add(new Userrole().setUrusid(uc.getUcusid()).setUrrlid("editor"));
		return finish("",response,HttpURLConnection.HTTP_OK);
	}

	@RequestMapping("/signin")
	public String signin(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String usid,@RequestParam String uspswd) throws UnsupportedEncodingException, JsonProcessingException
	{	User u=userMapper.get(usid,null);
		if(u==null||!u.getUspswd().equals(encryptor.entrypt(uspswd)))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		setUser(session,u.getUsid());
		u.setUspswd(null);
		return finish(u,response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/status")
	public String status(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		User u=userMapper.get((String)session.getAttribute("user"),null);
		u.setUspswd(null);
		return finish(u,response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/security")
	public String security(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String auth) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,auth))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/encrypt")
	public String encrypt(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String plain) throws JsonProcessingException, UnsupportedEncodingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		return finish(encryptor.entrypt(plain),response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/lock")
	public String lock(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String obj,@RequestParam String id) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,obj+"_wa")&&!checkSecurity(session,obj+"_wi"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!reserved.reserve(obj+"K"+id,session.getId()))
			return finish("",response,423);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping(value="/unlock")
	public String unlock(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String obj,@RequestParam String id) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,obj+"_wa")&&!checkSecurity(session,obj+"_wi"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		if(!reserved.remove(obj+"K"+id,session.getId()))
			return finish("",response,408);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/signout")
	public String signout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_NO_CONTENT);
		setUser(session,null);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@RequestMapping("/build")
	public String build(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam String lang) throws ClassNotFoundException, SQLException, IOException
	{	if(getUser(session)==null)
			return finish("",response,HttpURLConnection.HTTP_UNAUTHORIZED);
		if(!checkSecurity(session,"lang_wa"))
			return finish("",response,HttpURLConnection.HTTP_FORBIDDEN);
		return finish("",response,HttpURLConnection.HTTP_OK);
	}
	@Resource(name="jackson")
	public void setJackson(ObjectMapper jackson)
	{	super.setJackson(jackson);
	}
	@Resource(name="md5Encryptor")
	public void setEncryptor(MD5Encryptor encryptor)
	{	super.setEncryptor(encryptor);
	}
	@Resource(name="reserved")
	public void setReserved(Reserved reserved)
	{	super.setReserved(reserved);
	}
	@Resource(name="appMapper")
	public void settMapper(ToolMapper tMapper)
	{	super.settMapper(tMapper);
	}
	@Resource(name="userMapper")
	public UserMapper userMapper=null;
	@Value(value="${app.attachmentFolder}")
	private String attachmentFolder;
	@Value(value="#{usercreationMapper}")
	public UsercreationMapper usercreationMapper;
	@Value(value="#{userroleMapper}")
	public UserroleMapper userroleMapper;
	@Value(value="#{configurableCaptchaService}")
	public ConfigurableCaptchaService configurableCaptchaService;
	public SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
	private static FilterLogicExpr filterUsercreation(Integer ucid,Integer ucst)
	{
		FilterConditionExpr condition1=FilterConditionExpr.equalsNumber(Usercreation.FIELD_UCID,ucid);
		FilterConditionExpr condition2=FilterConditionExpr.equalsNumber(Usercreation.FIELD_UCST,ucst);
		return new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,condition2);
	}
	private static FilterLogicExpr filterUsercreation(String ucusid,Integer ucst,String uccrdd)
	{	FilterConditionExpr condition1=FilterConditionExpr.equalsString(Usercreation.FIELD_UCUSID,ucusid);
		FilterConditionExpr condition2=FilterConditionExpr.equalsNumber(Usercreation.FIELD_UCST,ucst);
		FilterConditionExpr condition3=FilterConditionExpr.equalsString(Usercreation.FIELD_UCCRDD,uccrdd);
		return new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,condition2,condition3);
	}
}
