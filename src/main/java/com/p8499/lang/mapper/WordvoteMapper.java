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
import com.p8499.lang.bean.Wordvote;

@Component("wordvoteMapper")
public interface WordvoteMapper extends BeanMapper<Wordvote,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F1042 WHERE WVID=#{wvid}")
	public boolean exists(@Param("wvid")Integer wvid);
	
	@Override
	@Select("SELECT WVID,WVWAID,WVSI,WVUSID,WVPO,WVUPDD,WVUPDT FROM public.F1042 WHERE WVID=#{wvid}")
	public Wordvote get(@Param("wvid")Integer wvid);
	
	@Override
	@Select("<script>"
		+ "<if test='mask.wvid or mask.wvwaid or mask.wvsi or mask.wvusid or mask.wvpo or mask.wvupdd or mask.wvupdt'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.wvid'>WVID, </if>"
		+ "<if test='mask.wvwaid'>WVWAID, </if>"
		+ "<if test='mask.wvsi'>WVSI, </if>"
		+ "<if test='mask.wvusid'>WVUSID, </if>"
		+ "<if test='mask.wvpo'>WVPO, </if>"
		+ "<if test='mask.wvupdd'>WVUPDD, </if>"
		+ "<if test='mask.wvupdt'>WVUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1042 WHERE WVID=#{wvid}"
		+ "</if>"
		+ "</script>")
	public Wordvote getWithMask(@Param("wvid")Integer wvid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F1042 (WVWAID,WVSI,WVUSID,WVPO,WVUPDD,WVUPDT) VALUES (#{bean.wvwaid},#{bean.wvsi},#{bean.wvusid},#{bean.wvpo},#{bean.wvupdd},#{bean.wvupdt})")
	@Options(useGeneratedKeys=true,keyProperty="bean.wvid")
	public void add(@Param("bean")Wordvote bean);
	
	@Override
	@Update("UPDATE public.F1042 SET WVWAID=#{bean.wvwaid},WVSI=#{bean.wvsi},WVUSID=#{bean.wvusid},WVPO=#{bean.wvpo},WVUPDD=#{bean.wvupdd},WVUPDT=#{bean.wvupdt} WHERE WVID=#{bean.wvid}")
	public void update(@Param("bean")Wordvote bean);
	
	@Override
	@Update("<script>"
		+ "<if test='mask.wvwaid or mask.wvsi or mask.wvusid or mask.wvpo or mask.wvupdd or mask.wvupdt'>"
		+ "UPDATE public.F1042 "
		+ "<set>"
		+ "<if test='mask.wvwaid'>WVWAID=#{bean.wvwaid}, </if>"
		+ "<if test='mask.wvsi'>WVSI=#{bean.wvsi}, </if>"
		+ "<if test='mask.wvusid'>WVUSID=#{bean.wvusid}, </if>"
		+ "<if test='mask.wvpo'>WVPO=#{bean.wvpo}, </if>"
		+ "<if test='mask.wvupdd'>WVUPDD=#{bean.wvupdd}, </if>"
		+ "<if test='mask.wvupdt'>WVUPDT=#{bean.wvupdt}, </if>"
		+ "</set>"
		+ "WHERE WVID=#{bean.wvid}"
		+ "</if>"
		+ "</script>")
	public void updateWithMask(@Param("bean")Wordvote bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F1042 WHERE WVID=#{wvid}")
	public boolean delete(@Param("wvid")Integer wvid);
	
	@Override
	@Select("<script>"
		+ "SELECT WVID,WVWAID,WVSI,WVUSID,WVPO,WVUPDD,WVUPDT FROM public.F1042 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Wordvote> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count);
	
	@Override
	@Select("<script>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.wvid'>WVID, </if>"
		+ "<if test='mask.wvwaid'>WVWAID, </if>"
		+ "<if test='mask.wvsi'>WVSI, </if>"
		+ "<if test='mask.wvusid'>WVUSID, </if>"
		+ "<if test='mask.wvpo'>WVPO, </if>"
		+ "<if test='mask.wvupdd'>WVUPDD, </if>"
		+ "<if test='mask.wvupdt'>WVUPDT, </if>"
		+ "</trim>"
		+ "FROM public.F1042 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Wordvote> queryWithMask(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F1042 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Select("SELECT if(MAX(wvsi),null,0)+1 FROM public.F1042 WHERE wvwaid=#{wvwaid}")
	public Integer nextWvsi(@Param("wvwaid")Integer wvwaid);
	
	@Override
	@Select("<script>"
		+ "SELECT SUM(C)=0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1042 WHERE WVWAID=#{bean.wvwaid} AND WVSI=#{bean.wvsi}<if test='bean.wvid!=null'> AND WVID!=#{bean.wvid}</if> "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F1042 WHERE WVWAID=#{bean.wvwaid} AND WVUSID=#{bean.wvusid}<if test='bean.wvid!=null'> AND WVID!=#{bean.wvid}</if> "
		+ ") T"
		+ "</script>")
	public boolean unique(@Param("bean")Wordvote bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1041 WHERE WAID=#{bean.wvwaid} "
		+ "UNION ALL SELECT COUNT(*) C FROM public.F0301 WHERE USID=#{bean.wvusid} "
		+ ") T")
	public boolean referencing(@Param("bean")Wordvote bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1041 WHERE WAID=#{wvwaid}")
	public boolean referencingWvwaid(@Param("wvwaid")Integer wvwaid);
	
	@Select("SELECT COUNT(*)>0 FROM public.F0301 WHERE USID=#{wvusid}")
	public boolean referencingWvusid(@Param("wvusid")String wvusid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Wordvote bean);
}