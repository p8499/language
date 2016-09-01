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
import com.p8499.lang.bean.Chunkcriteria;

@Component("chunkcriteriaMapper")
public interface ChunkcriteriaMapper extends BeanMapper<Chunkcriteria,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1021 WHERE CCID=#{ccid}")
	public boolean exists(@Param("ccid")Integer ccid);
	
	@Override
	@Select("SELECT CCID,CCCPID,CCSI,CCTK,CCTG FROM public.F1021 WHERE CCID=#{ccid}")
	public Chunkcriteria get(@Param("ccid")Integer ccid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.ccid or mask.cccpid or mask.ccsi or mask.cctk or mask.cctg'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.ccid'>CCID, </if>"
		+ "<if test='mask.cccpid'>CCCPID, </if>"
		+ "<if test='mask.ccsi'>CCSI, </if>"
		+ "<if test='mask.cctk'>CCTK, </if>"
		+ "<if test='mask.cctg'>CCTG, </if>"
		+ "</trim>"
		+ "FROM public.F1021 WHERE CCID=#{ccid}"
		+ "</if>"
		+ "</script>")
	public Chunkcriteria getWithMask(@Param("ccid")Integer ccid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1021 (CCCPID,CCSI,CCTK,CCTG) VALUES (#{bean.cccpid},#{bean.ccsi},#{bean.cctk},#{bean.cctg})")
	@Options(useGeneratedKeys=true,keyProperty="bean.ccid")
	public void add(@Param("bean")Chunkcriteria bean);
	
	@Override
	@Update("UPDATE public.F1021 SET CCCPID=#{bean.cccpid},CCSI=#{bean.ccsi},CCTK=#{bean.cctk},CCTG=#{bean.cctg} WHERE CCID=#{bean.ccid}")
	public void update(@Param("bean")Chunkcriteria bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.cccpid or mask.ccsi or mask.cctk or mask.cctg'>"
		+ "UPDATE public.F1021 "
		+ "<set>"
		+ "<if test='mask.cccpid'>CCCPID=#{bean.cccpid}, </if>"
		+ "<if test='mask.ccsi'>CCSI=#{bean.ccsi}, </if>"
		+ "<if test='mask.cctk'>CCTK=#{bean.cctk}, </if>"
		+ "<if test='mask.cctg'>CCTG=#{bean.cctg}, </if>"
		+ "</set>"
		+ "WHERE CCID=#{bean.ccid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Chunkcriteria bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1021 WHERE CCID=#{ccid}")
	public boolean delete(@Param("ccid")Integer ccid);
	
	@Override
	@Select("<script>"
		+ "SELECT CCID,CCCPID,CCSI,CCTK,CCTG FROM public.F1021 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Chunkcriteria> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.ccid'>CCID, </if>"
		+ "<if test='mask.cccpid'>CCCPID, </if>"
		+ "<if test='mask.ccsi'>CCSI, </if>"
		+ "<if test='mask.cctk'>CCTK, </if>"
		+ "<if test='mask.cctg'>CCTG, </if>"
		+ "</trim>"
		+ "FROM public.F1021 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Chunkcriteria> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1021 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT if(MAX(ccsi),null,0)+1 FROM public.F1021 WHERE cccpid=#{cccpid}")
	public Integer nextCcsi(@Param("cccpid")Integer cccpid);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Chunkcriteria bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1020 WHERE CPID=#{bean.cccpid} "
		+ ") T")
	public boolean referencing(@Param("bean")Chunkcriteria bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1020 WHERE CPID=#{cccpid}")
	public boolean referencingCccpid(@Param("cccpid")Integer cccpid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Chunkcriteria bean);
}