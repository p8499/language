package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Segment implements Bean
{	public static final String TABLE="public.F1130";
	public static final String VIEW="public.F1130";
	public static final String NAME="Segment";
	public static final String FIELD_TRASID="TRASID";
	public static final String FIELD_TRPI="TRPI";
	public static final String FIELD_TRHZ="TRHZ";
	public static final String FIELD_TRST="TRST";
	public static final String FIELD_TRUSID="TRUSID";
	public static final String FIELD_TRUPDD="TRUPDD";
	public static final String FIELD_TRUPDT="TRUPDT";
	public static final Integer TRST_DISABLED=-1;
	public static final Integer TRST_ENABLED=0;
	protected Integer trasid=null;
	protected String trpi=null;
	protected String trhz=null;
	protected Integer trst=null;
	protected String trusid=null;
	protected String trupdd=null;
	protected String trupdt=null;

	public Segment(Integer trasid,String trpi,String trhz,Integer trst,String trusid,String trupdd,String trupdt)
	{	this.trasid=trasid;
		this.trpi=trpi;
		this.trhz=trhz;
		this.trst=trst;
		this.trusid=trusid;
		this.trupdd=trupdd;
		this.trupdt=trupdt;
	}
	public Segment()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Segment clone()
	{
		return new Segment(trasid,trpi,trhz,trst,trusid,trupdd,trupdt);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTrasid()
	{	return trasid;
	}
	public Segment setTrasid(Integer trasid)
	{	this.trasid=trasid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=1024)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTrpi()
	{	return trpi;
	}
	public Segment setTrpi(String trpi)
	{	this.trpi=trpi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=128)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTrhz()
	{	return trhz;
	}
	public Segment setTrhz(String trhz)
	{	this.trhz=trhz;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTrst()
	{	return trst;
	}
	public Segment setTrst(Integer trst)
	{	this.trst=trst;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTrusid()
	{	return trusid;
	}
	public Segment setTrusid(String trusid)
	{	this.trusid=trusid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTrupdd()
	{	return trupdd;
	}
	public Segment setTrupdd(String trupdd)
	{	this.trupdd=trupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTrupdt()
	{	return trupdt;
	}
	public Segment setTrupdt(String trupdt)
	{	this.trupdt=trupdt;
		return this;
	}
}