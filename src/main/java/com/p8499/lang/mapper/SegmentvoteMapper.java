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
import com.p8499.lang.bean.Segmentvote;

@Component("SegmentvoteMapper")
public interface SegmentvoteMapper extends BeanMapper<Segmentvote,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1132 WHERE TVID=#{tvid}")
	public boolean exists(@Param("tvid")Integer tvid);
	
	@Override
	@Select("SELECT TVID,TVTAID,TVSI,TVUSID,TVPO,TAUPDD,TAUPDT FROM public.F1132 WHERE TVID=#{tvid}")
	public Segmentvote get(@Param("tvid")Integer tvid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.tvid or mask.tvtaid or mask.tvsi or mask.tvusid or mask.tvpo or mask.taupdd or mask.taupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.tvid'>TVID, </if>"
		+ "<if test='mask.tvtaid'>TVTAID, </if>"
		+ "<if test='mask.tvsi'>TVSI, </if>"
		+ "<if test='mask.tvusid'>TVUSID, </if>"
		+ "<if test='mask.tvpo'>TVPO, </if>"
		+ "<if test='mask.taupdd'>TAUPDD, </if>"
		+ "<if test='mask.taupdt'>TAUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1132 WHERE TVID=#{tvid}"
		+ "</if>"
		+ "</script>")
	public Segmentvote getWithMask(@Param("tvid")Integer tvid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1132 (TVTAID,TVSI,TVUSID,TVPO,TAUPDD,TAUPDT) VALUES (#{bean.tvtaid},#{bean.tvsi},#{bean.tvusid},#{bean.tvpo},#{bean.taupdd},#{bean.taupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.tvid")
	public void add(@Param("bean")Segmentvote bean);
	
	@Override
	@Update("UPDATE public.F1132 SET TVTAID=#{bean.tvtaid},TVSI=#{bean.tvsi},TVUSID=#{bean.tvusid},TVPO=#{bean.tvpo},TAUPDD=#{bean.taupdd},TAUPDT=#{bean.taupdt} WHERE TVID=#{bean.tvid}")
	public void update(@Param("bean")Segmentvote bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.tvtaid or mask.tvsi or mask.tvusid or mask.tvpo or mask.taupdd or mask.taupdt'>"
		+ "UPDATE public.F1132 "
		+ "<set>"
		+ "<if test='mask.tvtaid'>TVTAID=#{bean.tvtaid}, </if>"
		+ "<if test='mask.tvsi'>TVSI=#{bean.tvsi}, </if>"
		+ "<if test='mask.tvusid'>TVUSID=#{bean.tvusid}, </if>"
		+ "<if test='mask.tvpo'>TVPO=#{bean.tvpo}, </if>"
		+ "<if test='mask.taupdd'>TAUPDD=#{bean.taupdd}, </if>"
		+ "<if test='mask.taupdt'>TAUPDT=#{bean.taupdt}, </if>"
		+ "</set>"
		+ "WHERE TVID=#{bean.tvid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Segmentvote bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1132 WHERE TVID=#{tvid}")
	public boolean delete(@Param("tvid")Integer tvid);
	
	@Override
	@Select("<script>"
		+ "SELECT TVID,TVTAID,TVSI,TVUSID,TVPO,TAUPDD,TAUPDT FROM public.F1132 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Segmentvote> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.tvid'>TVID, </if>"
		+ "<if test='mask.tvtaid'>TVTAID, </if>"
		+ "<if test='mask.tvsi'>TVSI, </if>"
		+ "<if test='mask.tvusid'>TVUSID, </if>"
		+ "<if test='mask.tvpo'>TVPO, </if>"
		+ "<if test='mask.taupdd'>TAUPDD, </if>"
		+ "<if test='mask.taupdt'>TAUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1132 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Segmentvote> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1132 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT if(MAX(tvsi),null,0)+1 FROM public.F1132 WHERE tvtaid=#{tvtaid}")
	public Integer nextTvsi(@Param("tvtaid")Integer tvtaid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1132 WHERE TVTAID=#{bean.tvtaid} AND TVSI=#{bean.tvsi}<if test='bean.tvid!=null'> AND TVID!=#{bean.tvid}</if> "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1132 WHERE TVTAID=#{bean.tvtaid} AND TVUSID=#{bean.tvusid}<if test='bean.tvid!=null'> AND TVID!=#{bean.tvid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Segmentvote bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1131 WHERE TAID=#{bean.tvtaid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.tvusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Segmentvote bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1131 WHERE TAID=#{tvtaid}")
	public boolean referencingTvtaid(@Param("tvtaid")Integer tvtaid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{tvusid}")
	public boolean referencingTvusid(@Param("tvusid")String tvusid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Segmentvote bean);
}