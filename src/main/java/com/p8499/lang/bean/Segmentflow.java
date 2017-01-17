package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Segmentflow implements Bean
{	public static final String TABLE="public.F1131";
	public static final String VIEW="public.F1131";
	public static final String NAME="Segmentflow";
	public static final String FIELD_TAID="TAID";
	public static final String FIELD_TAASID="TAASID";
	public static final String FIELD_TASI="TASI";
	public static final String FIELD_TAPI="TAPI";
	public static final String FIELD_TAHZ="TAHZ";
	public static final String FIELD_TAST="TAST";
	public static final String FIELD_TAUSID="TAUSID";
	public static final String FIELD_TACRDD="TACRDD";
	public static final String FIELD_TACRDT="TACRDT";
	public static final String FIELD_TAUPDD="TAUPDD";
	public static final String FIELD_TAUPDT="TAUPDT";
	public static final Integer TAST_VOTING=1;
	public static final Integer TAST_PASSED=2;
	public static final Integer TAST_DENIED=3;
	public static final Integer TAST_RECALLED=4;
	protected Integer taid=null;
	protected Integer taasid=null;
	protected Integer tasi=null;
	protected String tapi=null;
	protected String tahz=null;
	protected Integer tast=null;
	protected String tausid=null;
	protected String tacrdd=null;
	protected String tacrdt=null;
	protected String taupdd=null;
	protected String taupdt=null;

	public Segmentflow(Integer taid,Integer taasid,Integer tasi,String tapi,String tahz,Integer tast,String tausid,String tacrdd,String tacrdt,String taupdd,String taupdt)
	{	this.taid=taid;
		this.taasid=taasid;
		this.tasi=tasi;
		this.tapi=tapi;
		this.tahz=tahz;
		this.tast=tast;
		this.tausid=tausid;
		this.tacrdd=tacrdd;
		this.tacrdt=tacrdt;
		this.taupdd=taupdd;
		this.taupdt=taupdt;
	}
	public Segmentflow()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Segmentflow clone()
	{
		return new Segmentflow(taid,taasid,tasi,tapi,tahz,tast,tausid,tacrdd,tacrdt,taupdd,taupdt);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getTaid()
	{	return taid;
	}
	public Segmentflow setTaid(Integer taid)
	{	this.taid=taid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTaasid()
	{	return taasid;
	}
	public Segmentflow setTaasid(Integer taasid)
	{	this.taasid=taasid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTasi()
	{	return tasi;
	}
	public Segmentflow setTasi(Integer tasi)
	{	this.tasi=tasi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=1024)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTapi()
	{	return tapi;
	}
	public Segmentflow setTapi(String tapi)
	{	this.tapi=tapi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=128)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTahz()
	{	return tahz;
	}
	public Segmentflow setTahz(String tahz)
	{	this.tahz=tahz;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTast()
	{	return tast;
	}
	public Segmentflow setTast(Integer tast)
	{	this.tast=tast;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTausid()
	{	return tausid;
	}
	public Segmentflow setTausid(String tausid)
	{	this.tausid=tausid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTacrdd()
	{	return tacrdd;
	}
	public Segmentflow setTacrdd(String tacrdd)
	{	this.tacrdd=tacrdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTacrdt()
	{	return tacrdt;
	}
	public Segmentflow setTacrdt(String tacrdt)
	{	this.tacrdt=tacrdt;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTaupdd()
	{	return taupdd;
	}
	public Segmentflow setTaupdd(String taupdd)
	{	this.taupdd=taupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTaupdt()
	{	return taupdt;
	}
	public Segmentflow setTaupdt(String taupdt)
	{	this.taupdt=taupdt;
		return this;
	}
}