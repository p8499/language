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
import com.p8499.lang.bean.Sentence;

@Component("sentenceMapper")
public interface SentenceMapper extends BeanMapper<Sentence,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1120 WHERE ASID=#{asid}")
	public boolean exists(@Param("asid")Integer asid);
	
	@Override
	@Select("SELECT ASID,ASATID,ASSI,ASCONT,ASST,ASUSID,ASUPDD,ASUPDT FROM public.F1120 WHERE ASID=#{asid}")
	public Sentence get(@Param("asid")Integer asid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.asid or mask.asatid or mask.assi or mask.ascont or mask.asst or mask.asusid or mask.asupdd or mask.asupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.asid'>ASID, </if>"
		+ "<if test='mask.asatid'>ASATID, </if>"
		+ "<if test='mask.assi'>ASSI, </if>"
		+ "<if test='mask.ascont'>ASCONT, </if>"
		+ "<if test='mask.asst'>ASST, </if>"
		+ "<if test='mask.asusid'>ASUSID, </if>"
		+ "<if test='mask.asupdd'>ASUPDD, </if>"
		+ "<if test='mask.asupdt'>ASUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1120 WHERE ASID=#{asid}"
		+ "</if>"
		+ "</script>")
	public Sentence getWithMask(@Param("asid")Integer asid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1120 (ASATID,ASSI,ASCONT,ASST,ASUSID,ASUPDD,ASUPDT) VALUES (#{bean.asatid},#{bean.assi},#{bean.ascont},#{bean.asst},#{bean.asusid},#{bean.asupdd},#{bean.asupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.asid")
	public void add(@Param("bean")Sentence bean);
	
	@Override
	@Update("UPDATE public.F1120 SET ASATID=#{bean.asatid},ASSI=#{bean.assi},ASCONT=#{bean.ascont},ASST=#{bean.asst},ASUSID=#{bean.asusid},ASUPDD=#{bean.asupdd},ASUPDT=#{bean.asupdt} WHERE ASID=#{bean.asid}")
	public void update(@Param("bean")Sentence bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.asatid or mask.assi or mask.ascont or mask.asst or mask.asusid or mask.asupdd or mask.asupdt'>"
		+ "UPDATE public.F1120 "
		+ "<set>"
		+ "<if test='mask.asatid'>ASATID=#{bean.asatid}, </if>"
		+ "<if test='mask.assi'>ASSI=#{bean.assi}, </if>"
		+ "<if test='mask.ascont'>ASCONT=#{bean.ascont}, </if>"
		+ "<if test='mask.asst'>ASST=#{bean.asst}, </if>"
		+ "<if test='mask.asusid'>ASUSID=#{bean.asusid}, </if>"
		+ "<if test='mask.asupdd'>ASUPDD=#{bean.asupdd}, </if>"
		+ "<if test='mask.asupdt'>ASUPDT=#{bean.asupdt}, </if>"
		+ "</set>"
		+ "WHERE ASID=#{bean.asid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Sentence bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1120 WHERE ASID=#{asid}")
	public boolean delete(@Param("asid")Integer asid);
	
	@Override
	@Select("<script>"
		+ "SELECT ASID,ASATID,ASSI,ASCONT,ASST,ASUSID,ASUPDD,ASUPDT FROM public.F1120 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Sentence> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.asid'>ASID, </if>"
		+ "<if test='mask.asatid'>ASATID, </if>"
		+ "<if test='mask.assi'>ASSI, </if>"
		+ "<if test='mask.ascont'>ASCONT, </if>"
		+ "<if test='mask.asst'>ASST, </if>"
		+ "<if test='mask.asusid'>ASUSID, </if>"
		+ "<if test='mask.asupdd'>ASUPDD, </if>"
		+ "<if test='mask.asupdt'>ASUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1120 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Sentence> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1120 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT if(MAX(assi),null,0)+10 FROM public.F1120 WHERE asatid=#{asatid}")
	public Integer nextAssi(@Param("asatid")Integer asatid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1120 WHERE ASATID=#{bean.asatid} AND ASSI=#{bean.assi}<if test='bean.asid!=null'> AND ASID!=#{bean.asid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Sentence bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1110 WHERE ATID=#{bean.asatid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.asusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Sentence bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1110 WHERE ATID=#{asatid}")
	public boolean referencingAsatid(@Param("asatid")Integer asatid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{asusid}")
	public boolean referencingAsusid(@Param("asusid")String asusid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1130 WHERE TRASID=#{bean.asid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1131 WHERE TAASID=#{bean.asid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1140 WHERE TGASID=#{bean.asid} "
		+ ") T")
	public boolean referenced(@Param("bean")Sentence bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1130 WHERE TRASID=#{asid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1131 WHERE TAASID=#{asid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1140 WHERE TGASID=#{asid} "
		+ ") T")
	public boolean referencedAsid(@Param("asid")Integer asid);
}