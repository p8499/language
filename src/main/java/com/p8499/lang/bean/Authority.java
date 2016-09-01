package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Authority implements Bean
{	public static final String TABLE="public.F0320";
	public static final String NAME="Authority";
	public static final String FIELD_AUID="AUID";
	public static final String FIELD_AUGRP="AUGRP";
	public static final String FIELD_AUNAME="AUNAME";
	protected String auid=null;
	protected Integer augrp=null;
	protected String auname=null;

	public Authority(String auid,Integer augrp,String auname)
	{	this.auid=auid;
		this.augrp=augrp;
		this.auname=auname;
	}
	public Authority()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Authority clone()
	{
		return new Authority(auid,augrp,auname);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAuid()
	{	return auid;
	}
	public Authority setAuid(String auid)
	{	this.auid=auid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAugrp()
	{	return augrp;
	}
	public Authority setAugrp(Integer augrp)
	{	this.augrp=augrp;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAuname()
	{	return auname;
	}
	public Authority setAuname(String auname)
	{	this.auname=auname;
		return this;
	}
}