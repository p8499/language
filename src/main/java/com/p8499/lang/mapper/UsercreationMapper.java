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
import com.p8499.lang.bean.Usercreation;

@Component("usercreationMapper")
public interface UsercreationMapper extends BeanMapper<Usercreation,Integer>
{	@Override
	@Select("SELECT COUNT(*)>0 FROM public.F0309 WHERE UCID=#{ucid}")
	public boolean exists(@Param("ucid")Integer ucid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<if test='mask.ucid or mask.uccrdd or mask.uccrdt or mask.ucusid or mask.ucpv or mask.ucmv or mask.ucst'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.ucid'>UCID, </if>"
		+ "<if test='mask.uccrdd'>UCCRDD, </if>"
		+ "<if test='mask.uccrdt'>UCCRDT, </if>"
		+ "<if test='mask.ucusid'>UCUSID, </if>"
		+ "<if test='mask.ucpv'>UCPV, </if>"
		+ "<if test='mask.ucmv'>UCMV, </if>"
		+ "<if test='mask.ucst'>UCST, </if>"
		+ "</trim>"
		+ "FROM public.F0309 WHERE UCID=#{ucid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT UCID,UCCRDD,UCCRDT,UCUSID,UCPV,UCMV,UCST FROM public.F0309 WHERE UCID=#{ucid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public Usercreation get(@Param("ucid")Integer ucid,@Param("mask")Mask mask);
	
	@Override
	@Insert("INSERT INTO public.F0309 (UCCRDD,UCCRDT,UCUSID,UCPV,UCMV,UCST) VALUES (#{bean.uccrdd},#{bean.uccrdt},#{bean.ucusid},#{bean.ucpv},#{bean.ucmv},#{bean.ucst})")
	@Options(useGeneratedKeys=true,keyProperty="bean.ucid")
	public void add(@Param("bean")Usercreation bean);
	
	@Override
	@Update("<script>"
		+ "<choose>"//todo
		+ "<when test='mask!=null'>"//todo
		+ "<if test='mask.uccrdd or mask.uccrdt or mask.ucusid or mask.ucpv or mask.ucmv or mask.ucst'>"
		+ "UPDATE public.F0309 "
		+ "<set>"
		+ "<if test='mask.uccrdd'>UCCRDD=#{bean.uccrdd}, </if>"
		+ "<if test='mask.uccrdt'>UCCRDT=#{bean.uccrdt}, </if>"
		+ "<if test='mask.ucusid'>UCUSID=#{bean.ucusid}, </if>"
		+ "<if test='mask.ucpv'>UCPV=#{bean.ucpv}, </if>"
		+ "<if test='mask.ucmv'>UCMV=#{bean.ucmv}, </if>"
		+ "<if test='mask.ucst'>UCST=#{bean.ucst}, </if>"
		+ "</set>"
		+ "WHERE UCID=#{bean.ucid}"
		+ "</if>"
		+ "</when>"
		+ "<otherwise>"
		+ "UPDATE public.F0309 SET UCCRDD=#{bean.uccrdd},UCCRDT=#{bean.uccrdt},UCUSID=#{bean.ucusid},UCPV=#{bean.ucpv},UCMV=#{bean.ucmv},UCST=#{bean.ucst} WHERE UCID=#{bean.ucid}"
		+ "</otherwise>"
		+ "</choose>"
		+ "</script>")
	public void update(@Param("bean")Usercreation bean,@Param("mask")Mask mask);
	
	@Override
	@Delete("DELETE FROM public.F0309 WHERE UCID=#{ucid}")
	public boolean delete(@Param("ucid")Integer ucid);
	
	@Override
	@Select("<script>"
		+ "<choose>"
		+ "<when test='mask!=null'>"
		+ "<trim prefix='SELECT' suffixOverrides=','>"
		+ "<if test='mask.ucid'>UCID, </if>"
		+ "<if test='mask.uccrdd'>UCCRDD, </if>"
		+ "<if test='mask.uccrdt'>UCCRDT, </if>"
		+ "<if test='mask.ucusid'>UCUSID, </if>"
		+ "<if test='mask.ucpv'>UCPV, </if>"
		+ "<if test='mask.ucmv'>UCMV, </if>"
		+ "<if test='mask.ucst'>UCST, </if>"
		+ "</trim>"
		+ "</when>"
		+ "<otherwise>"
		+ "SELECT UCID,UCCRDD,UCCRDT,UCUSID,UCPV,UCMV,UCST "
		+ "</otherwise>"
		+ "</choose>"
		+ "FROM public.F0309 "
		+ "<if test='filter!=null'>WHERE ${filter} </if>"
		+ "<if test='order!=null'>ORDER BY ${order} </if>"
		+ "LIMIT #{count} OFFSET #{start}"
		+ "</script>")
	public List<Usercreation> query(@Param("filter")String filter,@Param("order")String order,@Param("start")long start,@Param("count")long count,@Param("mask")Mask mask);
	
	@Override
	@Select("<script>"
		+ "SELECT COUNT(*) FROM public.F0309 "
		+ "<if test='filter!=null'>WHERE ${filter}</if> "
		+ "</script>")
	public long count(@Param("filter")String filter);
	
	@Override
	@Select("SELECT 1")
	public boolean unique(@Param("bean")Usercreation bean);
	
	@Override
	@Select("SELECT 1")
	public boolean referencing(@Param("bean")Usercreation bean);
	
	@Override
	@Select("SELECT 1")
	public boolean referenced(@Param("bean")Usercreation bean);
}