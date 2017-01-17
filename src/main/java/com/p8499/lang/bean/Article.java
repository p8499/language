package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Article implements Bean
{	public static final String TABLE="public.F1110";
	public static final String VIEW="public.V1110";
	public static final String NAME="Article";
	public static final String FIELD_ATID="ATID";
	public static final String FIELD_ATCGID="ATCGID";
	public static final String FIELD_ATSI="ATSI";
	public static final String FIELD_ATNAME="ATNAME";
	public static final String FIELD_ATUSID="ATUSID";
	public static final String FIELD_ATUPDD="ATUPDD";
	public static final String FIELD_ATUPDT="ATUPDT";
	public static final String FIELD_ATBRF="ATBRF";
	public static final String FIELD_ATCGNAME="ATCGNAME";
	public static final String FIELD_ATUSNAME="ATUSNAME";
	public static final String FIELD_ATCSA="ATCSA";
	public static final String FIELD_ATCSB="ATCSB";
	public static final String FIELD_ATCSC="ATCSC";
	public static final String FIELD_ATCSD="ATCSD";
	public static final String FIELD_ATCSE="ATCSE";
	public static final String FIELD_ATCSF="ATCSF";
	protected Integer atid=null;
	protected Integer atcgid=null;
	protected Integer atsi=null;
	protected String atname=null;
	protected String atusid=null;
	protected String atupdd=null;
	protected String atupdt=null;
	protected String atbrf=null;
	protected String atcgname=null;
	protected String atusname=null;
	protected Integer atcsa=null;
	protected Integer atcsb=null;
	protected Integer atcsc=null;
	protected Integer atcsd=null;
	protected Integer atcse=null;
	protected Integer atcsf=null;

	public Article(Integer atid,Integer atcgid,Integer atsi,String atname,String atusid,String atupdd,String atupdt,String atbrf,String atcgname,String atusname,Integer atcsa,Integer atcsb,Integer atcsc,Integer atcsd,Integer atcse,Integer atcsf)
	{	this.atid=atid;
		this.atcgid=atcgid;
		this.atsi=atsi;
		this.atname=atname;
		this.atusid=atusid;
		this.atupdd=atupdd;
		this.atupdt=atupdt;
		this.atbrf=atbrf;
		this.atcgname=atcgname;
		this.atusname=atusname;
		this.atcsa=atcsa;
		this.atcsb=atcsb;
		this.atcsc=atcsc;
		this.atcsd=atcsd;
		this.atcse=atcse;
		this.atcsf=atcsf;
	}
	public Article()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Article clone()
	{
		return new Article(atid,atcgid,atsi,atname,atusid,atupdd,atupdt,atbrf,atcgname,atusname,atcsa,atcsb,atcsc,atcsd,atcse,atcsf);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getAtid()
	{	return atid;
	}
	public Article setAtid(Integer atid)
	{	this.atid=atid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcgid()
	{	return atcgid;
	}
	public Article setAtcgid(Integer atcgid)
	{	this.atcgid=atcgid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtsi()
	{	return atsi;
	}
	public Article setAtsi(Integer atsi)
	{	this.atsi=atsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtname()
	{	return atname;
	}
	public Article setAtname(String atname)
	{	this.atname=atname;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtusid()
	{	return atusid;
	}
	public Article setAtusid(String atusid)
	{	this.atusid=atusid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtupdd()
	{	return atupdd;
	}
	public Article setAtupdd(String atupdd)
	{	this.atupdd=atupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtupdt()
	{	return atupdt;
	}
	public Article setAtupdt(String atupdt)
	{	this.atupdt=atupdt;
		return this;
	}
	
	@Size(max=96)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtbrf()
	{	return atbrf;
	}
	public Article setAtbrf(String atbrf)
	{	this.atbrf=atbrf;
		return this;
	}
	
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtcgname()
	{	return atcgname;
	}
	public Article setAtcgname(String atcgname)
	{	this.atcgname=atcgname;
		return this;
	}
	
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAtusname()
	{	return atusname;
	}
	public Article setAtusname(String atusname)
	{	this.atusname=atusname;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcsa()
	{	return atcsa;
	}
	public Article setAtcsa(Integer atcsa)
	{	this.atcsa=atcsa;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcsb()
	{	return atcsb;
	}
	public Article setAtcsb(Integer atcsb)
	{	this.atcsb=atcsb;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcsc()
	{	return atcsc;
	}
	public Article setAtcsc(Integer atcsc)
	{	this.atcsc=atcsc;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcsd()
	{	return atcsd;
	}
	public Article setAtcsd(Integer atcsd)
	{	this.atcsd=atcsd;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcse()
	{	return atcse;
	}
	public Article setAtcse(Integer atcse)
	{	this.atcse=atcse;
		return this;
	}
	
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAtcsf()
	{	return atcsf;
	}
	public Article setAtcsf(Integer atcsf)
	{	this.atcsf=atcsf;
		return this;
	}
}