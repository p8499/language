package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Role implements Bean
{	public static final String TABLE="public.F0310";
	public static final String VIEW="public.F0310";
	public static final String NAME="Role";
	public static final String FIELD_RLID="RLID";
	public static final String FIELD_RLNAME="RLNAME";
	protected String rlid=null;
	protected String rlname=null;

	public Role(String rlid,String rlname)
	{	this.rlid=rlid;
		this.rlname=rlname;
	}
	public Role()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Role clone()
	{
		return new Role(rlid,rlname);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getRlid()
	{	return rlid;
	}
	public Role setRlid(String rlid)
	{	this.rlid=rlid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getRlname()
	{	return rlname;
	}
	public Role setRlname(String rlname)
	{	this.rlname=rlname;
		return this;
	}
}