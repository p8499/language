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
import com.p8499.lang.bean.Tagging;

@Component("taggingMapper")
public interface TaggingMapper extends BeanMapper<Tagging,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1140 WHERE TGASID=#{tgasid}")
	public boolean exists(@Param("tgasid")Integer tgasid);
	
	@Override
	@Select("SELECT TGASID,TGCONT,TGST,TGUSID,TGUPDD,TGUPDT FROM public.F1140 WHERE TGASID=#{tgasid}")
	public Tagging get(@Param("tgasid")Integer tgasid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.tgasid or mask.tgcont or mask.tgst or mask.tgusid or mask.tgupdd or mask.tgupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.tgasid'>TGASID, </if>"
		+ "<if test='mask.tgcont'>TGCONT, </if>"
		+ "<if test='mask.tgst'>TGST, </if>"
		+ "<if test='mask.tgusid'>TGUSID, </if>"
		+ "<if test='mask.tgupdd'>TGUPDD, </if>"
		+ "<if test='mask.tgupdt'>TGUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1140 WHERE TGASID=#{tgasid}"
		+ "</if>"
		+ "</script>")
	public Tagging getWithMask(@Param("tgasid")Integer tgasid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1140 (TGASID,TGCONT,TGST,TGUSID,TGUPDD,TGUPDT) VALUES (#{bean.tgasid},#{bean.tgcont},#{bean.tgst},#{bean.tgusid},#{bean.tgupdd},#{bean.tgupdt})")
	public void add(@Param("bean")Tagging bean);
	
	@Override
	@Update("UPDATE public.F1140 SET TGCONT=#{bean.tgcont},TGST=#{bean.tgst},TGUSID=#{bean.tgusid},TGUPDD=#{bean.tgupdd},TGUPDT=#{bean.tgupdt} WHERE TGASID=#{bean.tgasid}")
	public void update(@Param("bean")Tagging bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.tgcont or mask.tgst or mask.tgusid or mask.tgupdd or mask.tgupdt'>"
		+ "UPDATE public.F1140 "
		+ "<set>"
		+ "<if test='mask.tgcont'>TGCONT=#{bean.tgcont}, </if>"
		+ "<if test='mask.tgst'>TGST=#{bean.tgst}, </if>"
		+ "<if test='mask.tgusid'>TGUSID=#{bean.tgusid}, </if>"
		+ "<if test='mask.tgupdd'>TGUPDD=#{bean.tgupdd}, </if>"
		+ "<if test='mask.tgupdt'>TGUPDT=#{bean.tgupdt}, </if>"
		+ "</set>"
		+ "WHERE TGASID=#{bean.tgasid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Tagging bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1140 WHERE TGASID=#{tgasid}")
	public boolean delete(@Param("tgasid")Integer tgasid);
	
	@Override
	@Select("<script>"
		+ "SELECT TGASID,TGCONT,TGST,TGUSID,TGUPDD,TGUPDT FROM public.F1140 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Tagging> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.tgasid'>TGASID, </if>"
		+ "<if test='mask.tgcont'>TGCONT, </if>"
		+ "<if test='mask.tgst'>TGST, </if>"
		+ "<if test='mask.tgusid'>TGUSID, </if>"
		+ "<if test='mask.tgupdd'>TGUPDD, </if>"
		+ "<if test='mask.tgupdt'>TGUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1140 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Tagging> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1140 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Tagging bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1120 WHERE ASID=#{bean.tgasid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.tgusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Tagging bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1120 WHERE ASID=#{tgasid}")
	public boolean referencingTgasid(@Param("tgasid")Integer tgasid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{tgusid}")
	public boolean referencingTgusid(@Param("tgusid")String tgusid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Tagging bean);
}