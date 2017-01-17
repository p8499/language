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
import com.p8499.lang.bean.Article;

@Component("articleMapper")
public interface ArticleMapper extends BeanMapper<Article,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.V1110 WHERE ATID=#{atid}")
	public boolean exists(@Param("atid")Integer atid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.atid or mask.atcgid or mask.atsi or mask.atname or mask.atusid or mask.atupdd or mask.atupdt or mask.atbrf or mask.atcgname or mask.atusname or mask.atcsa or mask.atcsb or mask.atcsc or mask.atcsd or mask.atcse or mask.atcsf'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.atid'>ATID, </if>"
		+ "<if test='mask.atcgid'>ATCGID, </if>"
		+ "<if test='mask.atsi'>ATSI, </if>"
		+ "<if test='mask.atname'>ATNAME, </if>"
		+ "<if test='mask.atusid'>ATUSID, </if>"
		+ "<if test='mask.atupdd'>ATUPDD, </if>"
		+ "<if test='mask.atupdt'>ATUPDT, </if>"
		+ "<if test='mask.atbrf'>ATBRF, </if>"
		+ "<if test='mask.atcgname'>ATCGNAME, </if>"
		+ "<if test='mask.atusname'>ATUSNAME, </if>"
		+ "<if test='mask.atcsa'>ATCSA, </if>"
		+ "<if test='mask.atcsb'>ATCSB, </if>"
		+ "<if test='mask.atcsc'>ATCSC, </if>"
		+ "<if test='mask.atcsd'>ATCSD, </if>"
		+ "<if test='mask.atcse'>ATCSE, </if>"
		+ "<if test='mask.atcsf'>ATCSF, </if>"
		+ "</trim>"
		+ "FROM public.V1110 WHERE ATID=#{atid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT ATID,ATCGID,ATSI,ATNAME,ATUSID,ATUPDD,ATUPDT,ATBRF,ATCGNAME,ATUSNAME,ATCSA,ATCSB,ATCSC,ATCSD,ATCSE,ATCSF FROM public.V1110 WHERE ATID=#{atid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Article get(@Param("atid")Integer atid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1110 (ATCGID,ATSI,ATNAME,ATUSID,ATUPDD,ATUPDT) VALUES (#{bean.atcgid},#{bean.atsi},#{bean.atname},#{bean.atusid},#{bean.atupdd},#{bean.atupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.atid")
	public void add(@Param("bean")Article bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.atcgid or mask.atsi or mask.atname or mask.atusid or mask.atupdd or mask.atupdt'>"
		+ "UPDATE public.F1110 "
		+ "<set>"
		+ "<if test='mask.atcgid'>ATCGID=#{bean.atcgid}, </if>"
		+ "<if test='mask.atsi'>ATSI=#{bean.atsi}, </if>"
		+ "<if test='mask.atname'>ATNAME=#{bean.atname}, </if>"
		+ "<if test='mask.atusid'>ATUSID=#{bean.atusid}, </if>"
		+ "<if test='mask.atupdd'>ATUPDD=#{bean.atupdd}, </if>"
		+ "<if test='mask.atupdt'>ATUPDT=#{bean.atupdt}, </if>"
		+ "</set>"
		+ "WHERE ATID=#{bean.atid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1110 SET ATCGID=#{bean.atcgid},ATSI=#{bean.atsi},ATNAME=#{bean.atname},ATUSID=#{bean.atusid},ATUPDD=#{bean.atupdd},ATUPDT=#{bean.atupdt} WHERE ATID=#{bean.atid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Article bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1110 WHERE ATID=#{atid}")
	public boolean delete(@Param("atid")Integer atid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.atid'>ATID, </if>"
		+ "<if test='mask.atcgid'>ATCGID, </if>"
		+ "<if test='mask.atsi'>ATSI, </if>"
		+ "<if test='mask.atname'>ATNAME, </if>"
		+ "<if test='mask.atusid'>ATUSID, </if>"
		+ "<if test='mask.atupdd'>ATUPDD, </if>"
		+ "<if test='mask.atupdt'>ATUPDT, </if>"
		+ "<if test='mask.atbrf'>ATBRF, </if>"
		+ "<if test='mask.atcgname'>ATCGNAME, </if>"
		+ "<if test='mask.atusname'>ATUSNAME, </if>"
		+ "<if test='mask.atcsa'>ATCSA, </if>"
		+ "<if test='mask.atcsb'>ATCSB, </if>"
		+ "<if test='mask.atcsc'>ATCSC, </if>"
		+ "<if test='mask.atcsd'>ATCSD, </if>"
		+ "<if test='mask.atcse'>ATCSE, </if>"
		+ "<if test='mask.atcsf'>ATCSF, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT ATID,ATCGID,ATSI,ATNAME,ATUSID,ATUPDD,ATUPDT,ATBRF,ATCGNAME,ATUSNAME,ATCSA,ATCSB,ATCSC,ATCSD,ATCSE,ATCSF "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.V1110 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Article> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.V1110 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT COALESCE(MAX(atsi),0)+1 FROM public.V1110 WHERE atcgid=#{atcgid}")
	public Integer nextAtsi(@Param("atcgid")Integer atcgid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1110 WHERE ATCGID=#{bean.atcgid} AND ATSI=#{bean.atsi}<if test='bean.atid!=null'> AND ATID!=#{bean.atid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Article bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1030 WHERE CGID=#{bean.atcgid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.atusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Article bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1030 WHERE CGID=#{atcgid}")
	public boolean referencingAtcgid(@Param("atcgid")Integer atcgid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{atusid}")
	public boolean referencingAtusid(@Param("atusid")String atusid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1120 WHERE ASATID=#{bean.atid} "
		+ ") T")
	public boolean referenced(@Param("bean")Article bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1120 WHERE ASATID=#{atid} "
		+ ") T")
	public boolean referencedAtid(@Param("atid")Integer atid);
}