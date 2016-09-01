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
import com.p8499.lang.bean.Authority;

@Component("authorityMapper")
public interface AuthorityMapper extends BeanMapper<Authority,String>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0320 WHERE AUID=#{auid}")
	public boolean exists(@Param("auid")String auid);
	
	@Override
	@Select("SELECT AUID,AUGRP,AUNAME FROM public.F0320 WHERE AUID=#{auid}")
	public Authority get(@Param("auid")String auid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.auid or mask.augrp or mask.auname'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.auid'>AUID, </if>"
		+ "<if test='mask.augrp'>AUGRP, </if>"
		+ "<if test='mask.auname'>AUNAME, </if>"
		+ "</trim>"
		+ "FROM public.F0320 WHERE AUID=#{auid}"
		+ "</if>"
		+ "</script>")
	public Authority getWithMask(@Param("auid")String auid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0320 (AUID,AUGRP,AUNAME) VALUES (#{bean.auid},#{bean.augrp},#{bean.auname})")
	public void add(@Param("bean")Authority bean);
	
	@Override
	@Update("UPDATE public.F0320 SET AUGRP=#{bean.augrp},AUNAME=#{bean.auname} WHERE AUID=#{bean.auid}")
	public void update(@Param("bean")Authority bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.augrp or mask.auname'>"
		+ "UPDATE public.F0320 "
		+ "<set>"
		+ "<if test='mask.augrp'>AUGRP=#{bean.augrp}, </if>"
		+ "<if test='mask.auname'>AUNAME=#{bean.auname}, </if>"
		+ "</set>"
		+ "WHERE AUID=#{bean.auid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Authority bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0320 WHERE AUID=#{auid}")
	public boolean delete(@Param("auid")String auid);
	
	@Override
	@Select("<script>"
		+ "SELECT AUID,AUGRP,AUNAME FROM public.F0320 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Authority> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.auid'>AUID, </if>"
		+ "<if test='mask.augrp'>AUGRP, </if>"
		+ "<if test='mask.auname'>AUNAME, </if>"
		+ "</trim>"
		+ "FROM public.F0320 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Authority> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0320 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Authority bean);
	
	@Override
	@Select("SELECT 1")
	public boolean referencing(@Param("bean")Authority bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0321 WHERE RAAUID=#{bean.auid} "
		+ ") T")
	public boolean referenced(@Param("bean")Authority bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0321 WHERE RAAUID=#{auid} "
		+ ") T")
	public boolean referencedAuid(@Param("auid")String auid);
}