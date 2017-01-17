package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Wordflow implements Bean
{	public static final String TABLE="public.F1041";
	public static final String VIEW="public.F1041";
	public static final String NAME="Wordflow";
	public static final String FIELD_WAID="WAID";
	public static final String FIELD_WAWOID="WAWOID";
	public static final String FIELD_WASI="WASI";
	public static final String FIELD_WAPT="WAPT";
	public static final String FIELD_WAST="WAST";
	public static final String FIELD_WAUSID="WAUSID";
	public static final String FIELD_WACRDD="WACRDD";
	public static final String FIELD_WACRDT="WACRDT";
	public static final String FIELD_WAUPDD="WAUPDD";
	public static final String FIELD_WAUPDT="WAUPDT";
	public static final Integer WAST_VOTING=1;
	public static final Integer WAST_PASSED=2;
	public static final Integer WAST_DENIED=3;
	protected Integer waid=null;
	protected Integer wawoid=null;
	protected Integer wasi=null;
	protected String wapt=null;
	protected Integer wast=null;
	protected String wausid=null;
	protected String wacrdd=null;
	protected String wacrdt=null;
	protected String waupdd=null;
	protected String waupdt=null;

	public Wordflow(Integer waid,Integer wawoid,Integer wasi,String wapt,Integer wast,String wausid,String wacrdd,String wacrdt,String waupdd,String waupdt)
	{	this.waid=waid;
		this.wawoid=wawoid;
		this.wasi=wasi;
		this.wapt=wapt;
		this.wast=wast;
		this.wausid=wausid;
		this.wacrdd=wacrdd;
		this.wacrdt=wacrdt;
		this.waupdd=waupdd;
		this.waupdt=waupdt;
	}
	public Wordflow()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Wordflow clone()
	{
		return new Wordflow(waid,wawoid,wasi,wapt,wast,wausid,wacrdd,wacrdt,waupdd,waupdt);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getWaid()
	{	return waid;
	}
	public Wordflow setWaid(Integer waid)
	{	this.waid=waid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWawoid()
	{	return wawoid;
	}
	public Wordflow setWawoid(Integer wawoid)
	{	this.wawoid=wawoid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWasi()
	{	return wasi;
	}
	public Wordflow setWasi(Integer wasi)
	{	this.wasi=wasi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=64)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWapt()
	{	return wapt;
	}
	public Wordflow setWapt(String wapt)
	{	this.wapt=wapt;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getWast()
	{	return wast;
	}
	public Wordflow setWast(Integer wast)
	{	this.wast=wast;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWausid()
	{	return wausid;
	}
	public Wordflow setWausid(String wausid)
	{	this.wausid=wausid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWacrdd()
	{	return wacrdd;
	}
	public Wordflow setWacrdd(String wacrdd)
	{	this.wacrdd=wacrdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWacrdt()
	{	return wacrdt;
	}
	public Wordflow setWacrdt(String wacrdt)
	{	this.wacrdt=wacrdt;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWaupdd()
	{	return waupdd;
	}
	public Wordflow setWaupdd(String waupdd)
	{	this.waupdd=waupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getWaupdt()
	{	return waupdt;
	}
	public Wordflow setWaupdt(String waupdt)
	{	this.waupdt=waupdt;
		return this;
	}
}