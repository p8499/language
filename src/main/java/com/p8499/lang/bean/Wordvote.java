package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Wordvote implements Bean
{	public static final String TABLE="public.F1042";
	public static final String NAME="Wordvote";
	public static final String FIELD_WVID="WVID";
	public static final String FIELD_WVWAID="WVWAID";
	public static final String FIELD_WVSI="WVSI";
	public static final String FIELD_WVUSID="WVUSID";
	public static final String FIELD_WVPO="WVPO";
	public static final String FIELD_WVUPDD="WVUPDD";
	public static final String FIELD_WVUPDT="WVUPDT";
	protected Integer wvid=null;
	protected Integer wvwaid=null;
	protected Integer wvsi=null;
	protected String wvusid=null;
	protected Integer wvpo=null;
	protected String wvupdd=null;
	protected String wvupdt=null;

	public Wordvote(Integer wvid,Integer wvwaid,Integer wvsi,String wvusid,Integer wvpo,String wvupdd,String wvupdt)
	{	this.wvid=wvid;
		this.wvwaid=wvwaid;
		this.wvsi=wvsi;
		this.wvusid=wvusid;
		this.wvpo=wvpo;
		this.wvupdd=wvupdd;
		this.wvupdt=wvupdt;
	}
	public Wordvote()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Wordvote clone()
	{
		return new Wordvote(wvid,wvwaid,wvsi,wvusid,wvpo,wvupdd,wvupdt);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getWvid()
	{	return wvid;
	}
	public Wordvote setWvid(Integer wvid)
	{	this.wvid=wvid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWvwaid()
	{	return wvwaid;
	}
	public Wordvote setWvwaid(Integer wvwaid)
	{	this.wvwaid=wvwaid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWvsi()
	{	return wvsi;
	}
	public Wordvote setWvsi(Integer wvsi)
	{	this.wvsi=wvsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWvusid()
	{	return wvusid;
	}
	public Wordvote setWvusid(String wvusid)
	{	this.wvusid=wvusid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWvpo()
	{	return wvpo;
	}
	public Wordvote setWvpo(Integer wvpo)
	{	this.wvpo=wvpo;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWvupdd()
	{	return wvupdd;
	}
	public Wordvote setWvupdd(String wvupdd)
	{	this.wvupdd=wvupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWvupdt()
	{	return wvupdt;
	}
	public Wordvote setWvupdt(String wvupdt)
	{	this.wvupdt=wvupdt;
		return this;
	}
}