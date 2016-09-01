package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Word implements Bean
{	public static final String TABLE="public.F1040";
	public static final String NAME="Word";
	public static final String FIELD_WOID="WOID";
	public static final String FIELD_WOLSID="WOLSID";
	public static final String FIELD_WOCT="WOCT";
	public static final String FIELD_WOPT="WOPT";
	public static final String FIELD_WOCL="WOCL";
	public static final String FIELD_WOST="WOST";
	public static final Integer WOST_DISABLED=-1;
	public static final Integer WOST_ENABLED=0;
	protected Integer woid=null;
	protected String wolsid=null;
	protected String woct=null;
	protected String wopt=null;
	protected String wocl=null;
	protected Integer wost=null;

	public Word(Integer woid,String wolsid,String woct,String wopt,String wocl,Integer wost)
	{	this.woid=woid;
		this.wolsid=wolsid;
		this.woct=woct;
		this.wopt=wopt;
		this.wocl=wocl;
		this.wost=wost;
	}
	public Word()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Word clone()
	{
		return new Word(woid,wolsid,woct,wopt,wocl,wost);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getWoid()
	{	return woid;
	}
	public Word setWoid(Integer woid)
	{	this.woid=woid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWolsid()
	{	return wolsid;
	}
	public Word setWolsid(String wolsid)
	{	this.wolsid=wolsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWoct()
	{	return woct;
	}
	public Word setWoct(String woct)
	{	this.woct=woct;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=64)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWopt()
	{	return wopt;
	}
	public Word setWopt(String wopt)
	{	this.wopt=wopt;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=4)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWocl()
	{	return wocl;
	}
	public Word setWocl(String wocl)
	{	this.wocl=wocl;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWost()
	{	return wost;
	}
	public Word setWost(Integer wost)
	{	this.wost=wost;
		return this;
	}
}