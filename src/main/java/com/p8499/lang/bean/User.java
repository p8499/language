package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class User implements Bean
{	public static final String TABLE="public.F0301";
	public static final String NAME="User";
	public static final String FIELD_USID="USID";
	public static final String FIELD_USPSWD="USPSWD";
	public static final String FIELD_USNAME="USNAME";
	public static final String FIELD_USST="USST";
	public static final String FIELD_USLSID="USLSID";
	public static final Integer USST_DISABLED=-1;
	public static final Integer USST_ENABLED=0;
	protected String usid=null;
	protected String uspswd=null;
	protected String usname=null;
	protected Integer usst=null;
	protected String uslsid=null;

	public User(String usid,String uspswd,String usname,Integer usst,String uslsid)
	{	this.usid=usid;
		this.uspswd=uspswd;
		this.usname=usname;
		this.usst=usst;
		this.uslsid=uslsid;
	}
	public User()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public User clone()
	{
		return new User(usid,uspswd,usname,usst,uslsid);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUsid()
	{	return usid;
	}
	public User setUsid(String usid)
	{	this.usid=usid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUspswd()
	{	return uspswd;
	}
	public User setUspswd(String uspswd)
	{	this.uspswd=uspswd;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUsname()
	{	return usname;
	}
	public User setUsname(String usname)
	{	this.usname=usname;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getUsst()
	{	return usst;
	}
	public User setUsst(Integer usst)
	{	this.usst=usst;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUslsid()
	{	return uslsid;
	}
	public User setUslsid(String uslsid)
	{	this.uslsid=uslsid;
		return this;
	}
}