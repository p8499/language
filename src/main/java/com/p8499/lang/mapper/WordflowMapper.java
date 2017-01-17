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
import com.p8499.lang.bean.Wordflow;

@Component("wordflowMapper")
public interface WordflowMapper extends BeanMapper<Wordflow,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1041 WHERE WAID=#{waid}")
	public boolean exists(@Param("waid")Integer waid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.waid or mask.wawoid or mask.wasi or mask.wapt or mask.wast or mask.wausid or mask.wacrdd or mask.wacrdt or mask.waupdd or mask.waupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.waid'>WAID, </if>"
		+ "<if test='mask.wawoid'>WAWOID, </if>"
		+ "<if test='mask.wasi'>WASI, </if>"
		+ "<if test='mask.wapt'>WAPT, </if>"
		+ "<if test='mask.wast'>WAST, </if>"
		+ "<if test='mask.wausid'>WAUSID, </if>"
		+ "<if test='mask.wacrdd'>WACRDD, </if>"
		+ "<if test='mask.wacrdt'>WACRDT, </if>"
		+ "<if test='mask.waupdd'>WAUPDD, </if>"
		+ "<if test='mask.waupdt'>WAUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1041 WHERE WAID=#{waid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT WAID,WAWOID,WASI,WAPT,WAST,WAUSID,WACRDD,WACRDT,WAUPDD,WAUPDT FROM public.F1041 WHERE WAID=#{waid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Wordflow get(@Param("waid")Integer waid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1041 (WAWOID,WASI,WAPT,WAST,WAUSID,WACRDD,WACRDT,WAUPDD,WAUPDT) VALUES (#{bean.wawoid},#{bean.wasi},#{bean.wapt},#{bean.wast},#{bean.wausid},#{bean.wacrdd},#{bean.wacrdt},#{bean.waupdd},#{bean.waupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.waid")
	public void add(@Param("bean")Wordflow bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.wawoid or mask.wasi or mask.wapt or mask.wast or mask.wausid or mask.wacrdd or mask.wacrdt or mask.waupdd or mask.waupdt'>"
		+ "UPDATE public.F1041 "
		+ "<set>"
		+ "<if test='mask.wawoid'>WAWOID=#{bean.wawoid}, </if>"
		+ "<if test='mask.wasi'>WASI=#{bean.wasi}, </if>"
		+ "<if test='mask.wapt'>WAPT=#{bean.wapt}, </if>"
		+ "<if test='mask.wast'>WAST=#{bean.wast}, </if>"
		+ "<if test='mask.wausid'>WAUSID=#{bean.wausid}, </if>"
		+ "<if test='mask.wacrdd'>WACRDD=#{bean.wacrdd}, </if>"
		+ "<if test='mask.wacrdt'>WACRDT=#{bean.wacrdt}, </if>"
		+ "<if test='mask.waupdd'>WAUPDD=#{bean.waupdd}, </if>"
		+ "<if test='mask.waupdt'>WAUPDT=#{bean.waupdt}, </if>"
		+ "</set>"
		+ "WHERE WAID=#{bean.waid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1041 SET WAWOID=#{bean.wawoid},WASI=#{bean.wasi},WAPT=#{bean.wapt},WAST=#{bean.wast},WAUSID=#{bean.wausid},WACRDD=#{bean.wacrdd},WACRDT=#{bean.wacrdt},WAUPDD=#{bean.waupdd},WAUPDT=#{bean.waupdt} WHERE WAID=#{bean.waid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Wordflow bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1041 WHERE WAID=#{waid}")
	public boolean delete(@Param("waid")Integer waid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.waid'>WAID, </if>"
		+ "<if test='mask.wawoid'>WAWOID, </if>"
		+ "<if test='mask.wasi'>WASI, </if>"
		+ "<if test='mask.wapt'>WAPT, </if>"
		+ "<if test='mask.wast'>WAST, </if>"
		+ "<if test='mask.wausid'>WAUSID, </if>"
		+ "<if test='mask.wacrdd'>WACRDD, </if>"
		+ "<if test='mask.wacrdt'>WACRDT, </if>"
		+ "<if test='mask.waupdd'>WAUPDD, </if>"
		+ "<if test='mask.waupdt'>WAUPDT, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT WAID,WAWOID,WASI,WAPT,WAST,WAUSID,WACRDD,WACRDT,WAUPDD,WAUPDT "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1041 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Wordflow> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1041 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT COALESCE(MAX(wasi),0)+1 FROM public.F1041 WHERE wawoid=#{wawoid}")
	public Integer nextWasi(@Param("wawoid")Integer wawoid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1041 WHERE WAWOID=#{bean.wawoid} AND WASI=#{bean.wasi}<if test='bean.waid!=null'> AND WAID!=#{bean.waid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Wordflow bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1040 WHERE WOID=#{bean.wawoid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.wausid} "
		+ ") T")
	public boolean referencing(@Param("bean")Wordflow bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1040 WHERE WOID=#{wawoid}")
	public boolean referencingWawoid(@Param("wawoid")Integer wawoid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{wausid}")
	public boolean referencingWausid(@Param("wausid")String wausid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1042 WHERE WVWAID=#{bean.waid} "
		+ ") T")
	public boolean referenced(@Param("bean")Wordflow bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1042 WHERE WVWAID=#{waid} "
		+ ") T")
	public boolean referencedWaid(@Param("waid")Integer waid);
}