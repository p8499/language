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
import com.p8499.lang.bean.Roleauthority;

@Component("roleauthorityMapper")
public interface RoleauthorityMapper extends BeanMapper<Roleauthority,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0321 WHERE RAID=#{raid}")
	public boolean exists(@Param("raid")Integer raid);
	
	@Override
	@Select("SELECT RAID,RARLID,RAAUID FROM public.F0321 WHERE RAID=#{raid}")
	public Roleauthority get(@Param("raid")Integer raid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.raid or mask.rarlid or mask.raauid'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.raid'>RAID, </if>"
		+ "<if test='mask.rarlid'>RARLID, </if>"
		+ "<if test='mask.raauid'>RAAUID, </if>"
		+ "</trim>"
		+ "FROM public.F0321 WHERE RAID=#{raid}"
		+ "</if>"
		+ "</script>")
	public Roleauthority getWithMask(@Param("raid")Integer raid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0321 (RARLID,RAAUID) VALUES (#{bean.rarlid},#{bean.raauid})")
	@Options(useGeneratedKeys=true,keyProperty="bean.raid")
	public void add(@Param("bean")Roleauthority bean);
	
	@Override
	@Update("UPDATE public.F0321 SET RARLID=#{bean.rarlid},RAAUID=#{bean.raauid} WHERE RAID=#{bean.raid}")
	public void update(@Param("bean")Roleauthority bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.rarlid or mask.raauid'>"
		+ "UPDATE public.F0321 "
		+ "<set>"
		+ "<if test='mask.rarlid'>RARLID=#{bean.rarlid}, </if>"
		+ "<if test='mask.raauid'>RAAUID=#{bean.raauid}, </if>"
		+ "</set>"
		+ "WHERE RAID=#{bean.raid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Roleauthority bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0321 WHERE RAID=#{raid}")
	public boolean delete(@Param("raid")Integer raid);
	
	@Override
	@Select("<script>"
		+ "SELECT RAID,RARLID,RAAUID FROM public.F0321 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Roleauthority> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.raid'>RAID, </if>"
		+ "<if test='mask.rarlid'>RARLID, </if>"
		+ "<if test='mask.raauid'>RAAUID, </if>"
		+ "</trim>"
		+ "FROM public.F0321 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Roleauthority> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0321 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0321 WHERE RARLID=#{bean.rarlid} AND RAAUID=#{bean.raauid}<if test='bean.raid!=null'> AND RAID!=#{bean.raid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Roleauthority bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F0320 WHERE AUID=#{bean.raauid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0310 WHERE RLID=#{bean.rarlid} "
		+ ") T")
	public boolean referencing(@Param("bean")Roleauthority bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0320 WHERE AUID=#{raauid}")
	public boolean referencingRaauid(@Param("raauid")String raauid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0310 WHERE RLID=#{rarlid}")
	public boolean referencingRarlid(@Param("rarlid")String rarlid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Roleauthority bean);
}