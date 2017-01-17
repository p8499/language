package com.p8499.lang.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component("appMapper")
public interface AppMapper extends com.p8499.mvc.database.ToolMapper
{	@Select("SELECT COUNT(*) FROM public.F0311 INNER JOIN public.F0321 ON URRLID=RARLID WHERE URUSID=#{user} AND RAAUID=#{auth}")
	public boolean checkSecurity(@Param("user") String user,@Param("auth") String auth);
}