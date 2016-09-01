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
import com.p8499.lang.bean.Userrole;

@Component("userroleMapper")
public interface UserroleMapper extends BeanMapper<Userrole,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0311 WHERE URID=#{urid}")
	public boolean exists(@Param("urid")Integer urid);
	
	@Override
	@Select("SELECT URID,URUSID,URRLID FROM public.F0311 WHERE URID=#{urid}")
	public Userrole get(@Param("urid")Integer urid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.urid or mask.urusid or mask.urrlid'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.urid'>URID, </if>"
		+ "<if test='mask.urusid'>URUSID, </if>"
		+ "<if test='mask.urrlid'>URRLID, </if>"
		+ "</trim>"
		+ "FROM public.F0311 WHERE URID=#{urid}"
		+ "</if>"
		+ "</script>")
	public Userrole getWithMask(@Param("urid")Integer urid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0311 (URUSID,URRLID) VALUES (#{bean.urusid},#{bean.urrlid})")
	@Options(useGeneratedKeys=true,keyProperty="bean.urid")
	public void add(@Param("bean")Userrole bean);
	
	@Override
	@Update("UPDATE public.F0311 SET URUSID=#{bean.urusid},URRLID=#{bean.urrlid} WHERE URID=#{bean.urid}")
	public void update(@Param("bean")Userrole bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.urusid or mask.urrlid'>"
		+ "UPDATE public.F0311 "
		+ "<set>"
		+ "<if test='mask.urusid'>URUSID=#{bean.urusid}, </if>"
		+ "<if test='mask.urrlid'>URRLID=#{bean.urrlid}, </if>"
		+ "</set>"
		+ "WHERE URID=#{bean.urid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Userrole bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0311 WHERE URID=#{urid}")
	public boolean delete(@Param("urid")Integer urid);
	
	@Override
	@Select("<script>"
		+ "SELECT URID,URUSID,URRLID FROM public.F0311 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Userrole> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.urid'>URID, </if>"
		+ "<if test='mask.urusid'>URUSID, </if>"
		+ "<if test='mask.urrlid'>URRLID, </if>"
		+ "</trim>"
		+ "FROM public.F0311 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Userrole> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0311 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0311 WHERE URUSID=#{bean.urusid} AND URRLID=#{bean.urrlid}<if test='bean.urid!=null'> AND URID!=#{bean.urid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Userrole bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.urusid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0310 WHERE RLID=#{bean.urrlid} "
		+ ") T")
	public boolean referencing(@Param("bean")Userrole bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{urusid}")
	public boolean referencingUrusid(@Param("urusid")String urusid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0310 WHERE RLID=#{urrlid}")
	public boolean referencingUrrlid(@Param("urrlid")String urrlid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Userrole bean);
}