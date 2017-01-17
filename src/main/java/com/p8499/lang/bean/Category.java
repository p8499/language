package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Category implements Bean
{	public static final String TABLE="public.F1030";
	public static final String VIEW="public.F1030";
	public static final String NAME="Category";
	public static final String FIELD_CGID="CGID";
	public static final String FIELD_CGLSID="CGLSID";
	public static final String FIELD_CGSI="CGSI";
	public static final String FIELD_CGPSI="CGPSI";
	public static final String FIELD_CGNAME="CGNAME";
	protected Integer cgid=null;
	protected String cglsid=null;
	protected Integer cgsi=null;
	protected Integer cgpsi=null;
	protected String cgname=null;

	public Category(Integer cgid,String cglsid,Integer cgsi,Integer cgpsi,String cgname)
	{	this.cgid=cgid;
		this.cglsid=cglsid;
		this.cgsi=cgsi;
		this.cgpsi=cgpsi;
		this.cgname=cgname;
	}
	public Category()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Category clone()
	{
		return new Category(cgid,cglsid,cgsi,cgpsi,cgname);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getCgid()
	{	return cgid;
	}
	public Category setCgid(Integer cgid)
	{	this.cgid=cgid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCglsid()
	{	return cglsid;
	}
	public Category setCglsid(String cglsid)
	{	this.cglsid=cglsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getCgsi()
	{	return cgsi;
	}
	public Category setCgsi(Integer cgsi)
	{	this.cgsi=cgsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getCgpsi()
	{	return cgpsi;
	}
	public Category setCgpsi(Integer cgpsi)
	{	this.cgpsi=cgpsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCgname()
	{	return cgname;
	}
	public Category setCgname(String cgname)
	{	this.cgname=cgname;
		return this;
	}
}