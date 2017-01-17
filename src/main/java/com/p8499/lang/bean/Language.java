package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Language implements Bean
{	public static final String TABLE="public.F1010";
	public static final String VIEW="public.F1010";
	public static final String NAME="Language";
	public static final String FIELD_LSID="LSID";
	public static final String FIELD_LSNAME="LSNAME";
	public static final String FIELD_LSLOC="LSLOC";
	public static final String FIELD_LSSORT="LSSORT";
	protected String lsid=null;
	protected String lsname=null;
	protected String lsloc=null;
	protected Integer lssort=null;

	public Language(String lsid,String lsname,String lsloc,Integer lssort)
	{	this.lsid=lsid;
		this.lsname=lsname;
		this.lsloc=lsloc;
		this.lssort=lssort;
	}
	public Language()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Language clone()
	{
		return new Language(lsid,lsname,lsloc,lssort);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLsid()
	{	return lsid;
	}
	public Language setLsid(String lsid)
	{	this.lsid=lsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLsname()
	{	return lsname;
	}
	public Language setLsname(String lsname)
	{	this.lsname=lsname;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=128)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLsloc()
	{	return lsloc;
	}
	public Language setLsloc(String lsloc)
	{	this.lsloc=lsloc;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLssort()
	{	return lssort;
	}
	public Language setLssort(Integer lssort)
	{	this.lssort=lssort;
		return this;
	}
}