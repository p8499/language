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
import com.p8499.lang.bean.Segment;

@Component("segmentMapper")
public interface SegmentMapper extends BeanMapper<Segment,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1130 WHERE TRASID=#{trasid}")
	public boolean exists(@Param("trasid")Integer trasid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.trasid or mask.trpi or mask.trhz or mask.trst or mask.trusid or mask.trupdd or mask.trupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.trasid'>TRASID, </if>"
		+ "<if test='mask.trpi'>TRPI, </if>"
		+ "<if test='mask.trhz'>TRHZ, </if>"
		+ "<if test='mask.trst'>TRST, </if>"
		+ "<if test='mask.trusid'>TRUSID, </if>"
		+ "<if test='mask.trupdd'>TRUPDD, </if>"
		+ "<if test='mask.trupdt'>TRUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1130 WHERE TRASID=#{trasid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT TRASID,TRPI,TRHZ,TRST,TRUSID,TRUPDD,TRUPDT FROM public.F1130 WHERE TRASID=#{trasid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Segment get(@Param("trasid")Integer trasid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1130 (TRASID,TRPI,TRHZ,TRST,TRUSID,TRUPDD,TRUPDT) VALUES (#{bean.trasid},#{bean.trpi},#{bean.trhz},#{bean.trst},#{bean.trusid},#{bean.trupdd},#{bean.trupdt})")
	public void add(@Param("bean")Segment bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.trpi or mask.trhz or mask.trst or mask.trusid or mask.trupdd or mask.trupdt'>"
		+ "UPDATE public.F1130 "
		+ "<set>"
		+ "<if test='mask.trpi'>TRPI=#{bean.trpi}, </if>"
		+ "<if test='mask.trhz'>TRHZ=#{bean.trhz}, </if>"
		+ "<if test='mask.trst'>TRST=#{bean.trst}, </if>"
		+ "<if test='mask.trusid'>TRUSID=#{bean.trusid}, </if>"
		+ "<if test='mask.trupdd'>TRUPDD=#{bean.trupdd}, </if>"
		+ "<if test='mask.trupdt'>TRUPDT=#{bean.trupdt}, </if>"
		+ "</set>"
		+ "WHERE TRASID=#{bean.trasid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1130 SET TRPI=#{bean.trpi},TRHZ=#{bean.trhz},TRST=#{bean.trst},TRUSID=#{bean.trusid},TRUPDD=#{bean.trupdd},TRUPDT=#{bean.trupdt} WHERE TRASID=#{bean.trasid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Segment bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1130 WHERE TRASID=#{trasid}")
	public boolean delete(@Param("trasid")Integer trasid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.trasid'>TRASID, </if>"
		+ "<if test='mask.trpi'>TRPI, </if>"
		+ "<if test='mask.trhz'>TRHZ, </if>"
		+ "<if test='mask.trst'>TRST, </if>"
		+ "<if test='mask.trusid'>TRUSID, </if>"
		+ "<if test='mask.trupdd'>TRUPDD, </if>"
		+ "<if test='mask.trupdt'>TRUPDT, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT TRASID,TRPI,TRHZ,TRST,TRUSID,TRUPDD,TRUPDT "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1130 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Segment> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1130 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Segment bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.V1120 WHERE ASID=#{bean.trasid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.trusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Segment bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.V1120 WHERE ASID=#{trasid}")
	public boolean referencingTrasid(@Param("trasid")Integer trasid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{trusid}")
	public boolean referencingTrusid(@Param("trusid")String trusid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Segment bean);
}