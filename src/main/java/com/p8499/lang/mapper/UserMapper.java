package com.p8499.lang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.User;

@Component("userMapper")
public interface UserMapper extends BeanMapper<User,String>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{usid}")
	public boolean exists(@Param("usid")String usid);
	
	@Override
	@Select("SELECT USID,USPSWD,USNAME,USST,USLSID FROM public.F0301 WHERE USID=#{usid}")
	public User get(@Param("usid")String usid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.usid or mask.uspswd or mask.usname or mask.usst or mask.uslsid'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.usid'>USID, </if>"
		+ "<if test='mask.uspswd'>USPSWD, </if>"
		+ "<if test='mask.usname'>USNAME, </if>"
		+ "<if test='mask.usst'>USST, </if>"
		+ "<if test='mask.uslsid'>USLSID, </if>"
		+ "</trim>"
		+ "FROM public.F0301 WHERE USID=#{usid}"
		+ "</if>"
		+ "</script>")
	public User getWithMask(@Param("usid")String usid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0301 (USID,USPSWD,USNAME,USST,USLSID) VALUES (#{bean.usid},#{bean.uspswd},#{bean.usname},#{bean.usst},#{bean.uslsid})")
	public void add(@Param("bean")User bean);
	
	@Override
	@Update("UPDATE public.F0301 SET USPSWD=#{bean.uspswd},USNAME=#{bean.usname},USST=#{bean.usst},USLSID=#{bean.uslsid} WHERE USID=#{bean.usid}")
	public void update(@Param("bean")User bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.uspswd or mask.usname or mask.usst or mask.uslsid'>"
		+ "UPDATE public.F0301 "
		+ "<set>"
		+ "<if test='mask.uspswd'>USPSWD=#{bean.uspswd}, </if>"
		+ "<if test='mask.usname'>USNAME=#{bean.usname}, </if>"
		+ "<if test='mask.usst'>USST=#{bean.usst}, </if>"
		+ "<if test='mask.uslsid'>USLSID=#{bean.uslsid}, </if>"
		+ "</set>"
		+ "WHERE USID=#{bean.usid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")User bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0301 WHERE USID=#{usid}")
	public boolean delete(@Param("usid")String usid);
	
	@Override
	@Select("<script>"
		+ "SELECT USID,USPSWD,USNAME,USST,USLSID FROM public.F0301 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<User> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.usid'>USID, </if>"
		+ "<if test='mask.uspswd'>USPSWD, </if>"
		+ "<if test='mask.usname'>USNAME, </if>"
		+ "<if test='mask.usst'>USST, </if>"
		+ "<if test='mask.uslsid'>USLSID, </if>"
		+ "</trim>"
		+ "FROM public.F0301 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<User> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0301 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")User bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.uslsid} "
		+ ") T")
	public boolean referencing(@Param("bean")User bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{uslsid}")
	public boolean referencingUslsid(@Param("uslsid")String uslsid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0311 WHERE URUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1110 WHERE ATUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1120 WHERE ASUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1130 WHERE TRUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1131 WHERE TAUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1132 WHERE TVUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1140 WHERE TGUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1041 WHERE WAUSID=#{bean.usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1042 WHERE WVUSID=#{bean.usid} "
		+ ") T")
	public boolean referenced(@Param("bean")User bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0311 WHERE URUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1110 WHERE ATUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1120 WHERE ASUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1130 WHERE TRUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1131 WHERE TAUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1132 WHERE TVUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1140 WHERE TGUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1041 WHERE WAUSID=#{usid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1042 WHERE WVUSID=#{usid} "
		+ ") T")
	public boolean referencedUsid(@Param("usid")String usid);
}