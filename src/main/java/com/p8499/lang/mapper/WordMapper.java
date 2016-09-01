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
import com.p8499.lang.bean.Word;

@Component("wordMapper")
public interface WordMapper extends BeanMapper<Word,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1040 WHERE WOID=#{woid}")
	public boolean exists(@Param("woid")Integer woid);
	
	@Override
	@Select("SELECT WOID,WOLSID,WOCT,WOPT,WOCL,WOST FROM public.F1040 WHERE WOID=#{woid}")
	public Word get(@Param("woid")Integer woid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.woid or mask.wolsid or mask.woct or mask.wopt or mask.wocl or mask.wost'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.woid'>WOID, </if>"
		+ "<if test='mask.wolsid'>WOLSID, </if>"
		+ "<if test='mask.woct'>WOCT, </if>"
		+ "<if test='mask.wopt'>WOPT, </if>"
		+ "<if test='mask.wocl'>WOCL, </if>"
		+ "<if test='mask.wost'>WOST, </if>"
		+ "</trim>"
		+ "FROM public.F1040 WHERE WOID=#{woid}"
		+ "</if>"
		+ "</script>")
	public Word getWithMask(@Param("woid")Integer woid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1040 (WOLSID,WOCT,WOPT,WOCL,WOST) VALUES (#{bean.wolsid},#{bean.woct},#{bean.wopt},#{bean.wocl},#{bean.wost})")
	@Options(useGeneratedKeys=true,keyProperty="bean.woid")
	public void add(@Param("bean")Word bean);
	
	@Override
	@Update("UPDATE public.F1040 SET WOLSID=#{bean.wolsid},WOCT=#{bean.woct},WOPT=#{bean.wopt},WOCL=#{bean.wocl},WOST=#{bean.wost} WHERE WOID=#{bean.woid}")
	public void update(@Param("bean")Word bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.wolsid or mask.woct or mask.wopt or mask.wocl or mask.wost'>"
		+ "UPDATE public.F1040 "
		+ "<set>"
		+ "<if test='mask.wolsid'>WOLSID=#{bean.wolsid}, </if>"
		+ "<if test='mask.woct'>WOCT=#{bean.woct}, </if>"
		+ "<if test='mask.wopt'>WOPT=#{bean.wopt}, </if>"
		+ "<if test='mask.wocl'>WOCL=#{bean.wocl}, </if>"
		+ "<if test='mask.wost'>WOST=#{bean.wost}, </if>"
		+ "</set>"
		+ "WHERE WOID=#{bean.woid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Word bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1040 WHERE WOID=#{woid}")
	public boolean delete(@Param("woid")Integer woid);
	
	@Override
	@Select("<script>"
		+ "SELECT WOID,WOLSID,WOCT,WOPT,WOCL,WOST FROM public.F1040 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Word> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.woid'>WOID, </if>"
		+ "<if test='mask.wolsid'>WOLSID, </if>"
		+ "<if test='mask.woct'>WOCT, </if>"
		+ "<if test='mask.wopt'>WOPT, </if>"
		+ "<if test='mask.wocl'>WOCL, </if>"
		+ "<if test='mask.wost'>WOST, </if>"
		+ "</trim>"
		+ "FROM public.F1040 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Word> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1040 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1040 WHERE WOLSID=#{bean.wolsid} AND WOCT=#{bean.woct} AND WOPT=#{bean.wopt}<if test='bean.woid!=null'> AND WOID!=#{bean.woid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Word bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.wolsid} "
		+ ") T")
	public boolean referencing(@Param("bean")Word bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{wolsid}")
	public boolean referencingWolsid(@Param("wolsid")String wolsid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1041 WHERE WAWOID=#{bean.woid} "
		+ ") T")
	public boolean referenced(@Param("bean")Word bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1041 WHERE WAWOID=#{woid} "
		+ ") T")
	public boolean referencedWoid(@Param("woid")Integer woid);
}