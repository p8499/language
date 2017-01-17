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
import com.p8499.lang.bean.Category;

@Component("categoryMapper")
public interface CategoryMapper extends BeanMapper<Category,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1030 WHERE CGID=#{cgid}")
	public boolean exists(@Param("cgid")Integer cgid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.cgid or mask.cglsid or mask.cgsi or mask.cgpsi or mask.cgname'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.cgid'>CGID, </if>"
		+ "<if test='mask.cglsid'>CGLSID, </if>"
		+ "<if test='mask.cgsi'>CGSI, </if>"
		+ "<if test='mask.cgpsi'>CGPSI, </if>"
		+ "<if test='mask.cgname'>CGNAME, </if>"
		+ "</trim>"
		+ "FROM public.F1030 WHERE CGID=#{cgid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT CGID,CGLSID,CGSI,CGPSI,CGNAME FROM public.F1030 WHERE CGID=#{cgid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Category get(@Param("cgid")Integer cgid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1030 (CGLSID,CGSI,CGPSI,CGNAME) VALUES (#{bean.cglsid},#{bean.cgsi},#{bean.cgpsi},#{bean.cgname})")
	@Options(useGeneratedKeys=true,keyProperty="bean.cgid")
	public void add(@Param("bean")Category bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.cglsid or mask.cgsi or mask.cgpsi or mask.cgname'>"
		+ "UPDATE public.F1030 "
		+ "<set>"
		+ "<if test='mask.cglsid'>CGLSID=#{bean.cglsid}, </if>"
		+ "<if test='mask.cgsi'>CGSI=#{bean.cgsi}, </if>"
		+ "<if test='mask.cgpsi'>CGPSI=#{bean.cgpsi}, </if>"
		+ "<if test='mask.cgname'>CGNAME=#{bean.cgname}, </if>"
		+ "</set>"
		+ "WHERE CGID=#{bean.cgid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1030 SET CGLSID=#{bean.cglsid},CGSI=#{bean.cgsi},CGPSI=#{bean.cgpsi},CGNAME=#{bean.cgname} WHERE CGID=#{bean.cgid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Category bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1030 WHERE CGID=#{cgid}")
	public boolean delete(@Param("cgid")Integer cgid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.cgid'>CGID, </if>"
		+ "<if test='mask.cglsid'>CGLSID, </if>"
		+ "<if test='mask.cgsi'>CGSI, </if>"
		+ "<if test='mask.cgpsi'>CGPSI, </if>"
		+ "<if test='mask.cgname'>CGNAME, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT CGID,CGLSID,CGSI,CGPSI,CGNAME "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1030 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Category> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1030 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1030 WHERE CGLSID=#{bean.cglsid} AND CGSI=#{bean.cgsi}<if test='bean.cgid!=null'> AND CGID!=#{bean.cgid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Category bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.cglsid} "
		+ ") T")
	public boolean referencing(@Param("bean")Category bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{cglsid}")
	public boolean referencingCglsid(@Param("cglsid")String cglsid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1110 WHERE ATCGID=#{bean.cgid} "
		+ ") T")
	public boolean referenced(@Param("bean")Category bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1110 WHERE ATCGID=#{cgid} "
		+ ") T")
	public boolean referencedCgid(@Param("cgid")Integer cgid);
}