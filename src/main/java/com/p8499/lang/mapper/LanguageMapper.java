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
import com.p8499.lang.bean.Language;

@Component("languageMapper")
public interface LanguageMapper extends BeanMapper<Language,String>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{lsid}")
	public boolean exists(@Param("lsid")String lsid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.lsid or mask.lsname or mask.lsloc or mask.lssort'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.lsid'>LSID, </if>"
		+ "<if test='mask.lsname'>LSNAME, </if>"
		+ "<if test='mask.lsloc'>LSLOC, </if>"
		+ "<if test='mask.lssort'>LSSORT, </if>"
		+ "</trim>"
		+ "FROM public.F1010 WHERE LSID=#{lsid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT LSID,LSNAME,LSLOC,LSSORT FROM public.F1010 WHERE LSID=#{lsid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Language get(@Param("lsid")String lsid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1010 (LSID,LSNAME,LSLOC,LSSORT) VALUES (#{bean.lsid},#{bean.lsname},#{bean.lsloc},#{bean.lssort})")
	public void add(@Param("bean")Language bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.lsname or mask.lsloc or mask.lssort'>"
		+ "UPDATE public.F1010 "
		+ "<set>"
		+ "<if test='mask.lsname'>LSNAME=#{bean.lsname}, </if>"
		+ "<if test='mask.lsloc'>LSLOC=#{bean.lsloc}, </if>"
		+ "<if test='mask.lssort'>LSSORT=#{bean.lssort}, </if>"
		+ "</set>"
		+ "WHERE LSID=#{bean.lsid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1010 SET LSNAME=#{bean.lsname},LSLOC=#{bean.lsloc},LSSORT=#{bean.lssort} WHERE LSID=#{bean.lsid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Language bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1010 WHERE LSID=#{lsid}")
	public boolean delete(@Param("lsid")String lsid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.lsid'>LSID, </if>"
		+ "<if test='mask.lsname'>LSNAME, </if>"
		+ "<if test='mask.lsloc'>LSLOC, </if>"
		+ "<if test='mask.lssort'>LSSORT, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT LSID,LSNAME,LSLOC,LSSORT "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1010 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Language> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1010 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Language bean);
	
	@Override
	@Select("SELECT 1")
	public boolean referencing(@Param("bean")Language bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0301 WHERE USLSID=#{bean.lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1020 WHERE CPLSID=#{bean.lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F9410 WHERE LPLSID=#{bean.lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1030 WHERE CGLSID=#{bean.lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1011 WHERE PNLSID=#{bean.lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1040 WHERE WOLSID=#{bean.lsid} "
		+ ") T")
	public boolean referenced(@Param("bean")Language bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0301 WHERE USLSID=#{lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1020 WHERE CPLSID=#{lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F9410 WHERE LPLSID=#{lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1030 WHERE CGLSID=#{lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1011 WHERE PNLSID=#{lsid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1040 WHERE WOLSID=#{lsid} "
		+ ") T")
	public boolean referencedLsid(@Param("lsid")String lsid);
}