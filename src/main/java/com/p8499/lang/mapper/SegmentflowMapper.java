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
import com.p8499.lang.bean.Segmentflow;

@Component("SegmentflowMapper")
public interface SegmentflowMapper extends BeanMapper<Segmentflow,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1131 WHERE TAID=#{taid}")
	public boolean exists(@Param("taid")Integer taid);
	
	@Override
	@Select("SELECT TAID,TAASID,TASI,TAPI,TAHZ,TAST,TAUSID,TACRDD,TACRDT,TAUPDD,TAUPDT FROM public.F1131 WHERE TAID=#{taid}")
	public Segmentflow get(@Param("taid")Integer taid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.taid or mask.taasid or mask.tasi or mask.tapi or mask.tahz or mask.tast or mask.tausid or mask.tacrdd or mask.tacrdt or mask.taupdd or mask.taupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.taid'>TAID, </if>"
		+ "<if test='mask.taasid'>TAASID, </if>"
		+ "<if test='mask.tasi'>TASI, </if>"
		+ "<if test='mask.tapi'>TAPI, </if>"
		+ "<if test='mask.tahz'>TAHZ, </if>"
		+ "<if test='mask.tast'>TAST, </if>"
		+ "<if test='mask.tausid'>TAUSID, </if>"
		+ "<if test='mask.tacrdd'>TACRDD, </if>"
		+ "<if test='mask.tacrdt'>TACRDT, </if>"
		+ "<if test='mask.taupdd'>TAUPDD, </if>"
		+ "<if test='mask.taupdt'>TAUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1131 WHERE TAID=#{taid}"
		+ "</if>"
		+ "</script>")
	public Segmentflow getWithMask(@Param("taid")Integer taid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1131 (TAASID,TASI,TAPI,TAHZ,TAST,TAUSID,TACRDD,TACRDT,TAUPDD,TAUPDT) VALUES (#{bean.taasid},#{bean.tasi},#{bean.tapi},#{bean.tahz},#{bean.tast},#{bean.tausid},#{bean.tacrdd},#{bean.tacrdt},#{bean.taupdd},#{bean.taupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.taid")
	public void add(@Param("bean")Segmentflow bean);
	
	@Override
	@Update("UPDATE public.F1131 SET TAASID=#{bean.taasid},TASI=#{bean.tasi},TAPI=#{bean.tapi},TAHZ=#{bean.tahz},TAST=#{bean.tast},TAUSID=#{bean.tausid},TACRDD=#{bean.tacrdd},TACRDT=#{bean.tacrdt},TAUPDD=#{bean.taupdd},TAUPDT=#{bean.taupdt} WHERE TAID=#{bean.taid}")
	public void update(@Param("bean")Segmentflow bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.taasid or mask.tasi or mask.tapi or mask.tahz or mask.tast or mask.tausid or mask.tacrdd or mask.tacrdt or mask.taupdd or mask.taupdt'>"
		+ "UPDATE public.F1131 "
		+ "<set>"
		+ "<if test='mask.taasid'>TAASID=#{bean.taasid}, </if>"
		+ "<if test='mask.tasi'>TASI=#{bean.tasi}, </if>"
		+ "<if test='mask.tapi'>TAPI=#{bean.tapi}, </if>"
		+ "<if test='mask.tahz'>TAHZ=#{bean.tahz}, </if>"
		+ "<if test='mask.tast'>TAST=#{bean.tast}, </if>"
		+ "<if test='mask.tausid'>TAUSID=#{bean.tausid}, </if>"
		+ "<if test='mask.tacrdd'>TACRDD=#{bean.tacrdd}, </if>"
		+ "<if test='mask.tacrdt'>TACRDT=#{bean.tacrdt}, </if>"
		+ "<if test='mask.taupdd'>TAUPDD=#{bean.taupdd}, </if>"
		+ "<if test='mask.taupdt'>TAUPDT=#{bean.taupdt}, </if>"
		+ "</set>"
		+ "WHERE TAID=#{bean.taid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Segmentflow bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1131 WHERE TAID=#{taid}")
	public boolean delete(@Param("taid")Integer taid);
	
	@Override
	@Select("<script>"
		+ "SELECT TAID,TAASID,TASI,TAPI,TAHZ,TAST,TAUSID,TACRDD,TACRDT,TAUPDD,TAUPDT FROM public.F1131 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Segmentflow> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.taid'>TAID, </if>"
		+ "<if test='mask.taasid'>TAASID, </if>"
		+ "<if test='mask.tasi'>TASI, </if>"
		+ "<if test='mask.tapi'>TAPI, </if>"
		+ "<if test='mask.tahz'>TAHZ, </if>"
		+ "<if test='mask.tast'>TAST, </if>"
		+ "<if test='mask.tausid'>TAUSID, </if>"
		+ "<if test='mask.tacrdd'>TACRDD, </if>"
		+ "<if test='mask.tacrdt'>TACRDT, </if>"
		+ "<if test='mask.taupdd'>TAUPDD, </if>"
		+ "<if test='mask.taupdt'>TAUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1131 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Segmentflow> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1131 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT if(MAX(tasi),null,0)+1 FROM public.F1131 WHERE taasid=#{taasid}")
	public Integer nextTasi(@Param("taasid")Integer taasid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1131 WHERE TAASID=#{bean.taasid} AND TASI=#{bean.tasi}<if test='bean.taid!=null'> AND TAID!=#{bean.taid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Segmentflow bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1120 WHERE ASID=#{bean.taasid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.tausid} "
		+ ") T")
	public boolean referencing(@Param("bean")Segmentflow bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1120 WHERE ASID=#{taasid}")
	public boolean referencingTaasid(@Param("taasid")Integer taasid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{tausid}")
	public boolean referencingTausid(@Param("tausid")String tausid);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1132 WHERE TVTAID=#{bean.taid} "
		+ ") T")
	public boolean referenced(@Param("bean")Segmentflow bean);
	
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1132 WHERE TVTAID=#{taid} "
		+ ") T")
	public boolean referencedTaid(@Param("taid")Integer taid);
}