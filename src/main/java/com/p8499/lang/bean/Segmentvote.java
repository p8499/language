package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Segmentvote implements Bean
{	public static final String TABLE="public.F1132";
	public static final String NAME="Segmentvote";
	public static final String FIELD_TVID="TVID";
	public static final String FIELD_TVTAID="TVTAID";
	public static final String FIELD_TVSI="TVSI";
	public static final String FIELD_TVUSID="TVUSID";
	public static final String FIELD_TVPO="TVPO";
	public static final String FIELD_TAUPDD="TAUPDD";
	public static final String FIELD_TAUPDT="TAUPDT";
	protected Integer tvid=null;
	protected Integer tvtaid=null;
	protected Integer tvsi=null;
	protected String tvusid=null;
	protected Integer tvpo=null;
	protected String taupdd=null;
	protected String taupdt=null;

	public Segmentvote(Integer tvid,Integer tvtaid,Integer tvsi,String tvusid,Integer tvpo,String taupdd,String taupdt)
	{	this.tvid=tvid;
		this.tvtaid=tvtaid;
		this.tvsi=tvsi;
		this.tvusid=tvusid;
		this.tvpo=tvpo;
		this.taupdd=taupdd;
		this.taupdt=taupdt;
	}
	public Segmentvote()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Segmentvote clone()
	{
		return new Segmentvote(tvid,tvtaid,tvsi,tvusid,tvpo,taupdd,taupdt);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getTvid()
	{	return tvid;
	}
	public Segmentvote setTvid(Integer tvid)
	{	this.tvid=tvid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTvtaid()
	{	return tvtaid;
	}
	public Segmentvote setTvtaid(Integer tvtaid)
	{	this.tvtaid=tvtaid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTvsi()
	{	return tvsi;
	}
	public Segmentvote setTvsi(Integer tvsi)
	{	this.tvsi=tvsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTvusid()
	{	return tvusid;
	}
	public Segmentvote setTvusid(String tvusid)
	{	this.tvusid=tvusid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTvpo()
	{	return tvpo;
	}
	public Segmentvote setTvpo(Integer tvpo)
	{	this.tvpo=tvpo;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTaupdd()
	{	return taupdd;
	}
	public Segmentvote setTaupdd(String taupdd)
	{	this.taupdd=taupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTaupdt()
	{	return taupdt;
	}
	public Segmentvote setTaupdt(String taupdt)
	{	this.taupdt=taupdt;
		return this;
	}
}