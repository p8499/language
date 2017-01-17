package com.p8499.lang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import com.p8499.mvc.database.BeanMapper;
import com.p8499.mvc.database.Mask;
import com.p8499.lang.bean.Pronounce;

@Component("pronounceMapper")
public interface PronounceMapper extends BeanMapper<Pronounce,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1011 WHERE PNID=#{pnid}")
	public boolean exists(@Param("pnid")Integer pnid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.pnid or mask.pnlsid or mask.pnct or mask.pnpi or mask.pntn or mask.pnco or mask.pnvo or mask.pncl or mask.pnrm'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.pnid'>PNID, </if>"
		+ "<if test='mask.pnlsid'>PNLSID, </if>"
		+ "<if test='mask.pnct'>PNCT, </if>"
		+ "<if test='mask.pnpi'>PNPI, </if>"
		+ "<if test='mask.pntn'>PNTN, </if>"
		+ "<if test='mask.pnco'>PNCO, </if>"
		+ "<if test='mask.pnvo'>PNVO, </if>"
		+ "<if test='mask.pncl'>PNCL, </if>"
		+ "<if test='mask.pnrm'>PNRM, </if>"
		+ "</trim>"
		+ "FROM public.F1011 WHERE PNID=#{pnid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT PNID,PNLSID,PNCT,PNPI,PNTN,PNCO,PNVO,PNCL,PNRM FROM public.F1011 WHERE PNID=#{pnid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Pronounce get(@Param("pnid")Integer pnid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1011 (PNLSID,PNCT,PNPI,PNTN,PNCO,PNVO,PNCL,PNRM) VALUES (#{bean.pnlsid},#{bean.pnct},#{bean.pnpi},#{bean.pntn},#{bean.pnco},#{bean.pnvo},#{bean.pncl},#{bean.pnrm})")
	@Options(useGeneratedKeys=true,keyProperty="bean.pnid")
	public void add(@Param("bean")Pronounce bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.pnlsid or mask.pnct or mask.pnpi or mask.pntn or mask.pnco or mask.pnvo or mask.pncl or mask.pnrm'>"
		+ "UPDATE public.F1011 "
		+ "<set>"
		+ "<if test='mask.pnlsid'>PNLSID=#{bean.pnlsid}, </if>"
		+ "<if test='mask.pnct'>PNCT=#{bean.pnct}, </if>"
		+ "<if test='mask.pnpi'>PNPI=#{bean.pnpi}, </if>"
		+ "<if test='mask.pntn'>PNTN=#{bean.pntn}, </if>"
		+ "<if test='mask.pnco'>PNCO=#{bean.pnco}, </if>"
		+ "<if test='mask.pnvo'>PNVO=#{bean.pnvo}, </if>"
		+ "<if test='mask.pncl'>PNCL=#{bean.pncl}, </if>"
		+ "<if test='mask.pnrm'>PNRM=#{bean.pnrm}, </if>"
		+ "</set>"
		+ "WHERE PNID=#{bean.pnid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1011 SET PNLSID=#{bean.pnlsid},PNCT=#{bean.pnct},PNPI=#{bean.pnpi},PNTN=#{bean.pntn},PNCO=#{bean.pnco},PNVO=#{bean.pnvo},PNCL=#{bean.pncl},PNRM=#{bean.pnrm} WHERE PNID=#{bean.pnid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Pronounce bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1011 WHERE PNID=#{pnid}")
	public boolean delete(@Param("pnid")Integer pnid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.pnid'>PNID, </if>"
		+ "<if test='mask.pnlsid'>PNLSID, </if>"
		+ "<if test='mask.pnct'>PNCT, </if>"
		+ "<if test='mask.pnpi'>PNPI, </if>"
		+ "<if test='mask.pntn'>PNTN, </if>"
		+ "<if test='mask.pnco'>PNCO, </if>"
		+ "<if test='mask.pnvo'>PNVO, </if>"
		+ "<if test='mask.pncl'>PNCL, </if>"
		+ "<if test='mask.pnrm'>PNRM, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT PNID,PNLSID,PNCT,PNPI,PNTN,PNCO,PNVO,PNCL,PNRM "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1011 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Pronounce> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1011 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Pronounce bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.pnlsid} "
		+ ") T")
	public boolean referencing(@Param("bean")Pronounce bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{pnlsid}")
	public boolean referencingPnlsid(@Param("pnlsid")String pnlsid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Pronounce bean);
}