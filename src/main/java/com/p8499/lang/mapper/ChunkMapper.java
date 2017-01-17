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
import com.p8499.lang.bean.Chunk;

@Component("chunkMapper")
public interface ChunkMapper extends BeanMapper<Chunk,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1020 WHERE CPID=#{cpid}")
	public boolean exists(@Param("cpid")Integer cpid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.cpid or mask.cplsid or mask.cpsi or mask.cptg or mask.cpft or mask.cpsort'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.cpid'>CPID, </if>"
		+ "<if test='mask.cplsid'>CPLSID, </if>"
		+ "<if test='mask.cpsi'>CPSI, </if>"
		+ "<if test='mask.cptg'>CPTG, </if>"
		+ "<if test='mask.cpft'>CPFT, </if>"
		+ "<if test='mask.cpsort'>CPSORT, </if>"
		+ "</trim>"
		+ "FROM public.F1020 WHERE CPID=#{cpid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT CPID,CPLSID,CPSI,CPTG,CPFT,CPSORT FROM public.F1020 WHERE CPID=#{cpid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Chunk get(@Param("cpid")Integer cpid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1020 (CPLSID,CPSI,CPTG,CPFT,CPSORT) VALUES (#{bean.cplsid},#{bean.cpsi},#{bean.cptg},#{bean.cpft},#{bean.cpsort})")
	@Options(useGeneratedKeys=true,keyProperty="bean.cpid")
	public void add(@Param("bean")Chunk bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.cplsid or mask.cpsi or mask.cptg or mask.cpft or mask.cpsort'>"
		+ "UPDATE public.F1020 "
		+ "<set>"
		+ "<if test='mask.cplsid'>CPLSID=#{bean.cplsid}, </if>"
		+ "<if test='mask.cpsi'>CPSI=#{bean.cpsi}, </if>"
		+ "<if test='mask.cptg'>CPTG=#{bean.cptg}, </if>"
		+ "<if test='mask.cpft'>CPFT=#{bean.cpft}, </if>"
		+ "<if test='mask.cpsort'>CPSORT=#{bean.cpsort}, </if>"
		+ "</set>"
		+ "WHERE CPID=#{bean.cpid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F1020 SET CPLSID=#{bean.cplsid},CPSI=#{bean.cpsi},CPTG=#{bean.cptg},CPFT=#{bean.cpft},CPSORT=#{bean.cpsort} WHERE CPID=#{bean.cpid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Chunk bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1020 WHERE CPID=#{cpid}")
	public boolean delete(@Param("cpid")Integer cpid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.cpid'>CPID, </if>"
		+ "<if test='mask.cplsid'>CPLSID, </if>"
		+ "<if test='mask.cpsi'>CPSI, </if>"
		+ "<if test='mask.cptg'>CPTG, </if>"
		+ "<if test='mask.cpft'>CPFT, </if>"
		+ "<if test='mask.cpsort'>CPSORT, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT CPID,CPLSID,CPSI,CPTG,CPFT,CPSORT "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F1020 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Chunk> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1020 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Chunk bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.cplsid} "
		+ ") T")
	public boolean referencing(@Param("bean")Chunk bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{cplsid}")
	public boolean referencingCplsid(@Param("cplsid")String cplsid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1021 WHERE CCCPID=#{bean.cpid} "
		+ ") T")
	public boolean referenced(@Param("bean")Chunk bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1021 WHERE CCCPID=#{cpid} "
		+ ") T")
	public boolean referencedCpid(@Param("cpid")Integer cpid);
}