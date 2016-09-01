package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Userrole implements Bean
{	public static final String TABLE="public.F0311";
	public static final String NAME="Userrole";
	public static final String FIELD_URID="URID";
	public static final String FIELD_URUSID="URUSID";
	public static final String FIELD_URRLID="URRLID";
	protected Integer urid=null;
	protected String urusid=null;
	protected String urrlid=null;

	public Userrole(Integer urid,String urusid,String urrlid)
	{	this.urid=urid;
		this.urusid=urusid;
		this.urrlid=urrlid;
	}
	public Userrole()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Userrole clone()
	{
		return new Userrole(urid,urusid,urrlid);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getUrid()
	{	return urid;
	}
	public Userrole setUrid(Integer urid)
	{	this.urid=urid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUrusid()
	{	return urusid;
	}
	public Userrole setUrusid(String urusid)
	{	this.urusid=urusid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUrrlid()
	{	return urrlid;
	}
	public Userrole setUrrlid(String urrlid)
	{	this.urrlid=urrlid;
		return this;
	}
}