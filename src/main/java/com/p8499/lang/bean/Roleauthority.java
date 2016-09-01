package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Roleauthority implements Bean
{	public static final String TABLE="public.F0321";
	public static final String NAME="Roleauthority";
	public static final String FIELD_RAID="RAID";
	public static final String FIELD_RARLID="RARLID";
	public static final String FIELD_RAAUID="RAAUID";
	protected Integer raid=null;
	protected String rarlid=null;
	protected String raauid=null;

	public Roleauthority(Integer raid,String rarlid,String raauid)
	{	this.raid=raid;
		this.rarlid=rarlid;
		this.raauid=raauid;
	}
	public Roleauthority()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Roleauthority clone()
	{
		return new Roleauthority(raid,rarlid,raauid);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getRaid()
	{	return raid;
	}
	public Roleauthority setRaid(Integer raid)
	{	this.raid=raid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getRarlid()
	{	return rarlid;
	}
	public Roleauthority setRarlid(String rarlid)
	{	this.rarlid=rarlid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getRaauid()
	{	return raauid;
	}
	public Roleauthority setRaauid(String raauid)
	{	this.raauid=raauid;
		return this;
	}
}