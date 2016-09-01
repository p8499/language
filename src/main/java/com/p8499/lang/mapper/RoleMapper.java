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
import com.p8499.lang.bean.Role;

@Component("roleMapper")
public interface RoleMapper extends BeanMapper<Role,String>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0310 WHERE RLID=#{rlid}")
	public boolean exists(@Param("rlid")String rlid);
	
	@Override
	@Select("SELECT RLID,RLNAME FROM public.F0310 WHERE RLID=#{rlid}")
	public Role get(@Param("rlid")String rlid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.rlid or mask.rlname'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.rlid'>RLID, </if>"
		+ "<if test='mask.rlname'>RLNAME, </if>"
		+ "</trim>"
		+ "FROM public.F0310 WHERE RLID=#{rlid}"
		+ "</if>"
		+ "</script>")
	public Role getWithMask(@Param("rlid")String rlid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0310 (RLID,RLNAME) VALUES (#{bean.rlid},#{bean.rlname})")
	public void add(@Param("bean")Role bean);
	
	@Override
	@Update("UPDATE public.F0310 SET RLNAME=#{bean.rlname} WHERE RLID=#{bean.rlid}")
	public void update(@Param("bean")Role bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.rlname'>"
		+ "UPDATE public.F0310 "
		+ "<set>"
		+ "<if test='mask.rlname'>RLNAME=#{bean.rlname}, </if>"
		+ "</set>"
		+ "WHERE RLID=#{bean.rlid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Role bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0310 WHERE RLID=#{rlid}")
	public boolean delete(@Param("rlid")String rlid);
	
	@Override
	@Select("<script>"
		+ "SELECT RLID,RLNAME FROM public.F0310 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Role> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.rlid'>RLID, </if>"
		+ "<if test='mask.rlname'>RLNAME, </if>"
		+ "</trim>"
		+ "FROM public.F0310 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Role> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0310 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Role bean);
	
	@Override
	@Select("SELECT 1")
	public boolean referencing(@Param("bean")Role bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0311 WHERE URRLID=#{bean.rlid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0321 WHERE RARLID=#{bean.rlid} "
		+ ") T")
	public boolean referenced(@Param("bean")Role bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0311 WHERE URRLID=#{rlid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0321 WHERE RARLID=#{rlid} "
		+ ") T")
	public boolean referencedRlid(@Param("rlid")String rlid);
}