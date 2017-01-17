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
import com.p8499.lang.bean.Languageparameter;

@Component("languageparameterMapper")
public interface LanguageparameterMapper extends BeanMapper<Languageparameter,String>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F9410 WHERE LPLSID=#{lplsid}")
	public boolean exists(@Param("lplsid")String lplsid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.lplsid or mask.lpsgng or mask.lpsgch or mask.lpsglf or mask.lpsgcw or mask.lpsgbw or mask.lpsgnb or mask.lpsgft or mask.lpsgfc or mask.lppong or mask.lppoch or mask.lppolf or mask.lppoft or mask.lppofc'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.lplsid'>LPLSID, </if>"
		+ "<if test='mask.lpsgng'>LPSGNG, </if>"
		+ "<if test='mask.lpsgch'>LPSGCH, </if>"
		+ "<if test='mask.lpsglf'>LPSGLF, </if>"
		+ "<if test='mask.lpsgcw'>LPSGCW, </if>"
		+ "<if test='mask.lpsgbw'>LPSGBW, </if>"
		+ "<if test='mask.lpsgnb'>LPSGNB, </if>"
		+ "<if test='mask.lpsgft'>LPSGFT, </if>"
		+ "<if test='mask.lpsgfc'>LPSGFC, </if>"
		+ "<if test='mask.lppong'>LPPONG, </if>"
		+ "<if test='mask.lppoch'>LPPOCH, </if>"
		+ "<if test='mask.lppolf'>LPPOLF, </if>"
		+ "<if test='mask.lppoft'>LPPOFT, </if>"
		+ "<if test='mask.lppofc'>LPPOFC, </if>"
		+ "</trim>"
		+ "FROM public.F9410 WHERE LPLSID=#{lplsid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT LPLSID,LPSGNG,LPSGCH,LPSGLF,LPSGCW,LPSGBW,LPSGNB,LPSGFT,LPSGFC,LPPONG,LPPOCH,LPPOLF,LPPOFT,LPPOFC FROM public.F9410 WHERE LPLSID=#{lplsid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Languageparameter get(@Param("lplsid")String lplsid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F9410 (LPLSID,LPSGNG,LPSGCH,LPSGLF,LPSGCW,LPSGBW,LPSGNB,LPSGFT,LPSGFC,LPPONG,LPPOCH,LPPOLF,LPPOFT,LPPOFC) VALUES (#{bean.lplsid},#{bean.lpsgng},#{bean.lpsgch},#{bean.lpsglf},#{bean.lpsgcw},#{bean.lpsgbw},#{bean.lpsgnb},#{bean.lpsgft},#{bean.lpsgfc},#{bean.lppong},#{bean.lppoch},#{bean.lppolf},#{bean.lppoft},#{bean.lppofc})")
	public void add(@Param("bean")Languageparameter bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.lpsgng or mask.lpsgch or mask.lpsglf or mask.lpsgcw or mask.lpsgbw or mask.lpsgnb or mask.lpsgft or mask.lpsgfc or mask.lppong or mask.lppoch or mask.lppolf or mask.lppoft or mask.lppofc'>"
		+ "UPDATE public.F9410 "
		+ "<set>"
		+ "<if test='mask.lpsgng'>LPSGNG=#{bean.lpsgng}, </if>"
		+ "<if test='mask.lpsgch'>LPSGCH=#{bean.lpsgch}, </if>"
		+ "<if test='mask.lpsglf'>LPSGLF=#{bean.lpsglf}, </if>"
		+ "<if test='mask.lpsgcw'>LPSGCW=#{bean.lpsgcw}, </if>"
		+ "<if test='mask.lpsgbw'>LPSGBW=#{bean.lpsgbw}, </if>"
		+ "<if test='mask.lpsgnb'>LPSGNB=#{bean.lpsgnb}, </if>"
		+ "<if test='mask.lpsgft'>LPSGFT=#{bean.lpsgft}, </if>"
		+ "<if test='mask.lpsgfc'>LPSGFC=#{bean.lpsgfc}, </if>"
		+ "<if test='mask.lppong'>LPPONG=#{bean.lppong}, </if>"
		+ "<if test='mask.lppoch'>LPPOCH=#{bean.lppoch}, </if>"
		+ "<if test='mask.lppolf'>LPPOLF=#{bean.lppolf}, </if>"
		+ "<if test='mask.lppoft'>LPPOFT=#{bean.lppoft}, </if>"
		+ "<if test='mask.lppofc'>LPPOFC=#{bean.lppofc}, </if>"
		+ "</set>"
		+ "WHERE LPLSID=#{bean.lplsid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F9410 SET LPSGNG=#{bean.lpsgng},LPSGCH=#{bean.lpsgch},LPSGLF=#{bean.lpsglf},LPSGCW=#{bean.lpsgcw},LPSGBW=#{bean.lpsgbw},LPSGNB=#{bean.lpsgnb},LPSGFT=#{bean.lpsgft},LPSGFC=#{bean.lpsgfc},LPPONG=#{bean.lppong},LPPOCH=#{bean.lppoch},LPPOLF=#{bean.lppolf},LPPOFT=#{bean.lppoft},LPPOFC=#{bean.lppofc} WHERE LPLSID=#{bean.lplsid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Languageparameter bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F9410 WHERE LPLSID=#{lplsid}")
	public boolean delete(@Param("lplsid")String lplsid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.lplsid'>LPLSID, </if>"
		+ "<if test='mask.lpsgng'>LPSGNG, </if>"
		+ "<if test='mask.lpsgch'>LPSGCH, </if>"
		+ "<if test='mask.lpsglf'>LPSGLF, </if>"
		+ "<if test='mask.lpsgcw'>LPSGCW, </if>"
		+ "<if test='mask.lpsgbw'>LPSGBW, </if>"
		+ "<if test='mask.lpsgnb'>LPSGNB, </if>"
		+ "<if test='mask.lpsgft'>LPSGFT, </if>"
		+ "<if test='mask.lpsgfc'>LPSGFC, </if>"
		+ "<if test='mask.lppong'>LPPONG, </if>"
		+ "<if test='mask.lppoch'>LPPOCH, </if>"
		+ "<if test='mask.lppolf'>LPPOLF, </if>"
		+ "<if test='mask.lppoft'>LPPOFT, </if>"
		+ "<if test='mask.lppofc'>LPPOFC, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT LPLSID,LPSGNG,LPSGCH,LPSGLF,LPSGCW,LPSGBW,LPSGNB,LPSGFT,LPSGFC,LPPONG,LPPOCH,LPPOLF,LPPOFT,LPPOFC "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F9410 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Languageparameter> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F9410 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Languageparameter bean);
	
	@Override
	@Select("SELECT SUM(C)>0 FROM( "
		+ "SELECT COUNT(*) C FROM public.F1010 WHERE LSID=#{bean.lplsid} "
		+ ") T")
	public boolean referencing(@Param("bean")Languageparameter bean);
	
	@Select("SELECT COUNT(*)>0 FROM public.F1010 WHERE LSID=#{lplsid}")
	public boolean referencingLplsid(@Param("lplsid")String lplsid);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Languageparameter bean);
}